module.exports = {
  // Change build paths to make them Maven compatible
  // see https://cli.vuejs.org/config/
  publicPath: '',
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        //ws: true,
        changeOrigin: true
      }
    },
  }
}