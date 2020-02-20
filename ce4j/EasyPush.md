`easypush-tag.bat`

```bash
@echo off

echo 自动化脚本 将A仓库develop分支的代码，推到B仓库

set timestamp=%date:~0,4%%date:~5,2%%date:~8,2%%time:~0,2%%time:~3,2%%time:~6,2%
set newrepo=origin2
set newtag=tag-%timestamp%

:: 清理目录
if exist ./arms-front (rd /s /q ./arms-front)
:: 拉代码
git clone http://192.168.0.152/itplh/easypush.git
:: 进入项目
cd ./arms-front
:: 切换到develop分支
git checkout develop
:: 增加git仓库 cq
git remote add %newrepo% git@192.168.0.153:jenkins-group/easypush.git
:: 推送代码到cq仓库
:: git push %newrepo%
:: 创建新tag
git tag %newtag%
:: 将新创建的tag推送到cq仓库
git push %newrepo% %newtag%

::输出空行
echo=

pause
```