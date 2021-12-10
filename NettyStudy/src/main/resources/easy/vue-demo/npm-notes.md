## 笔记

#### npm常用命令

1. npm -v 查看npm版本
2. npm install <module name> 使用npm安装模块
3. npm install <module name>@版本号 使用npm安装指定版本的模块
4. npm install <module name> -g 全局安装
5. npm list -g 查看所有全局安装的模块
6. npm list <module name> 查看某个模块的版本号
7. npm install -save <module name>  -save 在package文件的dependencies节点写入依赖, dependencies节点是运行时的依赖,发布后,即生产环境下还需要用的模块
8. npm install -save-dev <module name>  -save 在package文件的devDependencies节点写入依赖, devDependencies节点是开发时的依赖, 发布时用不到它,比如项目中使用的gulp,压缩css,js的模块, 这些模块在我们项目的部署是不需要的

#### package.json

 0. 生成package.json文件 ---> npm init / npm init --yes
 1. "jquery": "^3.6.0"  ---> 表示下载的时候会下载 3.x.x 的最新版本
 2. "jquery": "～3.6.0"  ---> 表示下载的时候会下载 3.6.x 的最新版本
 3. "jquery": "3.6.0"  ---> 表示下载的时候会下载 3.6.0 的版本

#### ES6兼容性问题

 1. 在线转换(这种编译会加大页面的渲染时间)
 2. 提前编译(推荐使用, 不影响浏览器渲染时间 比较通用的工具babel, jsx, traceur, es6-shim)
   ```javascript
      // 安装babel
      npm install babel-cli -g
      //在项目下创建 .babeirc 文件
      {
        "presets": ["es2015", "stage-2"],  //设置转码规则 
        "plugins": ["transform-runtime"]   //设置插件
      }
      // 安装插件
      npm install -save-dev babel-core babel-preset-es2015 babel-plugin-transform-runtime babel-preset-stage-2

      //编译整个src文件并将目录输出到lib目录, 这里的src的目录指的是需要转换的目录, lib指的是输出的内容的存放目录, -w 其实是-watch, 就是监听文件, 实时编译输出
      "build": "babel src -w -d lib"  //在package.json里面添加script
   ```

#### Yarn基本使用（参考npm）

 1. yarn config set registry https://registry.npm.taobao.org -g
 2. yarn init
 3. yarn add/remove jquery 增加删除模块

#### webpack5

 1. webpack是一个模块打包器(构建工具),它的主要目标是将javascript打包, 打包后的文件用于在浏览器中使用, 但它也能够胜任转换,打包或包裹任何资源
 2. 基本原理
    1. 树结构：在一个入口文件中引入所有资源, 形成所有依赖关系树状图
    2. 模块： 对于webpack来说所有的资源都是模块
    3. chunk: 打包过程中被操作的模块叫做chunk, 例如异步加载一个模块就是chunk
    4. bundle: 是最终的打包文件, 一般是多个chunk的集合
 3. 安装
   ```javascrpt
      // 安装
      npm i webpack webpack-cli -D -g
   ```
 4. 使用
   ```javascript
      //简单使用
      webpack --mode development
      webpack --mode prodyction
      //配置文件: 在项目目录下新建webpack.config.js
      entry: 入口文件, 表示webpack以哪个文件作为入口起点开始打包, 分析构建内部依赖视图
      output: 输出指示webpack打包后的资源bundles输出到哪里,怎么命名
      loader: 让webpack能处理那些非javascript资源css, img, 等,将他们处理成webpack可以识别的资源, 可以理解为一个翻译的过程(webpack本身只能理解js和json)
      plugins: 插件用于执行更广的任务, 插件的范围包括, 从打包优化和压缩, 一直到重新定义环境变量中的变量等
      mode: development/production
   ```