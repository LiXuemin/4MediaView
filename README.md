# 4MediaView
一套将多媒体资源转码, 存储, 分发的可拆分方案.

## 主要功能:
1. Office,txt转PDF/图片集
2. 图片压缩, 裁剪, 翻转等
3. 音视频转码
4. 文件存储, 兼容S3/AliyunOSS/FastDFS等
5. WEB UI

## 底层组件
1. OpenOffice,LibreOffice,Windows Office + jacob
2. ImageMagick
3. FFMpeg
4. S3 API, FastDFS, Minio
5. Spring Cloud

## 架构图示
V1-初步规划,架构图如下:
![microservice architecture](https://github.com/LiXuemin/media-service/blob/master/media-service.png)

- [ ] Storage Service
- [ ] Text Converter
- [ ] Image Converter
- [ ] Video/Audio Converter
- [ ] UI
