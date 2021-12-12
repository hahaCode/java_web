const {resolve} = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    /* 单入口，指定一个入口文件, 打包一个chunk,chunk名字是默认的, 输出一个bundle */
    entry: './src/index.js',
    /**
     * 多入口, 指定多个入口文件, 打包一个chunk,chunk名字是默认的, 输出一个bundle
     * entry: ["./src/a.js", "./src.b.js"],
     * 
     * 多入口, 指定多个入口文件, 打包多个chunk,chunk名字key,
     * entry: {
     *    one: './src/a.js',
     *    one: './src/a.js',
     * },
     * 
     * output: {
     *   filename: "[name].js",
     *   path: resolve(__dirname, 'build')
     * },
     * 
     * 特殊用法
     * entry: {
     *    one: ['./src/a1.js', './src/a2.js'], 
     *    two: './src/b.js',
     * }
     */
    output: {
        filename: "build.js",
        path: resolve(__dirname, 'build')
    },
    
    // 打包html
    plugins: [
        //默认会创建一个空的, 目的就是自动引入打包的资源
        //new HtmlWebpackPlugin()
        new HtmlWebpackPlugin({
            template: './src/index.html',
            filename: "demo.html",
            minify:{
                collapseWhitespace: true, //压缩的时候删除空格
                removeComments: true //移除空格
            }
        })
    ],
    //mode: 'production' //生产模式
    mode: 'development'
}

// 分别打包不同的html和js
// entry: {
//    vendor: ['./src/jquery.js', './src/common.js'], 
//    index: './src/index.js',
//    cart: './src/cart.js',
// },

// plugins: [
//     //默认会创建一个空的, 目的就是自动引入打包的资源
//     //new HtmlWebpackPlugin()
//     new HtmlWebpackPlugin({
//         template: './src/index.html',
//         filename: "demo.html",
//         chunks: ['index', 'vendor']
//     }),
//     new HtmlWebpackPlugin({
//         template: './src/cart.html',
//         filename: "cart.html",
//         chunks: ['cart', 'vendor']
//     })
// ],