//app.js
App({
  onLaunch: function () {
    var that = this;
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        console.log( res);
        wx.request({
          url: 'https://wedding.nobbican.com/user/token',
          method:'POST',
          header: {
            'content-type': 'application/json'
          },
          data: {
            code:res.code
          },
          success: tokenData => {
            console.log(tokenData)
            that.globalData.token = tokenData.data.data;
          }
        })
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        console.log("res=" + res);
        // if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
        wx.getUserInfo({
          success: res => {
            // 可以将 res 发送给后台解码出 unionId
            this.globalData.userInfo = res.userInfo
            // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
            // 所以此处加入 callback 以防止这种情况
            if (this.userInfoReadyCallback) {
              this.userInfoReadyCallback(res)
            }
          }
        })
        // }else{
        //   //提示用户需要授权才能登陆
        // }
      }
    })
  },
  globalData: {
    userInfo: null
  }
})