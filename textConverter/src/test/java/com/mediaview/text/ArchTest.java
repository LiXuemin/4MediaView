package com.mediaview.text;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.mediaview.text");

        noClasses()
            .that()
                .resideInAnyPackage("com.mediaview.text.service..")
            .or()
                .resideInAnyPackage("com.mediaview.text.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.mediaview.text.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
