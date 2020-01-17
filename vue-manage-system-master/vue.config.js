module.exports = {
    publicPath: './',
    assetsDir: 'static',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/api':{
                target:'http://192.168.8.9:8080',
                changeOrigin:true,
                ws: true,
                pathRewrite:{
                    '^/api':'/'
                }
            }
        },
        port: 8081     // 端口号
    },
    configureWebpack: {
        devtool: 'source-map'
      }

}