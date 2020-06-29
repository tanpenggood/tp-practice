# electron-vue-demo

使用`electron9` + `vue-cli3`开发跨平台桌面应用

### 搭建项目

```
# 准备vue-cli
cnpm uninstall vue-cli -g
cnpm install @vue/cli -g
vue -V

# 创建vue项目
vue create electron-vue-demo

# 进入工程根目录
cd electron-vue-demo

# 自动安装electron
vue add electron-builder

# 安装依赖包
yarn install
```

### 快速启动

```
# 进入工程根目录
cd electron-vue-demo

# 编译并启动APP
npm run electron:serve
```

### 打包APP
```
yarn electron:build
```

> ※注：在32位环境下打包生成的是32位APP，在64位环境下打包生成的是64位APP。

### Run your tests
```
yarn run test
```

### Lints and fixes files
```
yarn run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
