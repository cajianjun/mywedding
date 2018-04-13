var net = require('../../utils/net.js')
var reAuth = require('../../utils/reAuth.js')
var app = getApp();
Page({
  data: {
    height: 20,
    focus: false,
    words:"",
    toastHidden: true,  
    tooLong: '简单点，说话的方式简单点，不能超过100字',
    tooShort:"不能发送空消息，后台会报错的",
    thanks: "发送成功，感谢",
    toastText:"" ,
    hiddenmodalput: true
  },
  sendPost:function(){
    var that = this;
    if (app.globalData.userInfo) {
      if (that.data.words.length > 100) {
        that.setData({
          toastHidden: false,
          toastText: that.data.tooLong
        })
      } else if (that.data.words.trim().length == 0) {
        that.setData({
          toastHidden: false,
          toastText: that.data.tooShort
        })
      } else {
        net.post("/user/msg", that.data.words, resp => {
          that.setData({
            toastHidden: false,
            toastText: that.data.thanks
          })
        });
      }
    }
  }
  ,

  send: function () {
    var that = this;
    if (!app.globalData.userInfo){
      reAuth.reAuth(that.sendPost)
    }else{
      that.sendPost();
    }
    
    
  },
  textInput:function(e){
    this.setData({
      words: e.detail.value,
    });
  },
  textBlur:function(e){
    
    if(e.detail.value.length > 100){
      this.setData({
        toastHidden: false,
        toastText: this.data.tooLong
      })
    }
  },
  onToastChanged: function () {
    this.setData({ toastHidden: !this.data.toastHidden });
  },
  onLoad: function () {
    this.setData({
      toastHidden: true, //吐司  
      words:""
    })
  },
   //点击按钮痰喘指定的hiddenmodalput弹出框  
    modalinput: function () {
    this.setData({
      hiddenmodalput: !this.data.hiddenmodalput
    })
  },
  //取消按钮  
  cancel: function () {
    this.setData({
      hiddenmodalput: true
    });
  },
  //确认  
  confirm: function () {
    this.setData({
      hiddenmodalput: true
    })
    this.send();
  }   
})