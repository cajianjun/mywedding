var app = getApp();
function reAuth(cb){
  wx.showModal({
    title: '抱歉',
    content: '您点击了拒绝授权,将无法正常使用该功能,点击确定重新获取授权。',
    success: function (res) {
      wx.openSetting({
        success: (res) => {
          if (res.authSetting["scope.userInfo"]) {
            wx.getUserInfo({
              success: function (res) {
                var userInfo = res.userInfo;
                app.globalData.userInfo = userInfo;
                cb("OK");
              }
            })
          }
        }
        

      });
    },
    showCancel:false,
    fail:function (res){
      cb("NO")
    }
  })
}

module.exports = {
  reAuth: reAuth
}