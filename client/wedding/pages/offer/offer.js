//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    duration: 1000,
    loadingHidden: false, // loading
    userInfo: {},
    swiperCurrent: 0,
    selectCurrent: 0,
    categories: [],
    activeCategoryId: 0,
    goods: [],
    scrollTop: "0",
    loadingMoreHidden: true,

    hasNoCoupons: true,
    coupons: [],
    searchInput: '',
  },

  tabClick: function (e) {
    this.setData({
      activeCategoryId: e.currentTarget.id
    });
    this.getGoodsList(this.data.activeCategoryId);
  },
  //事件处理函数
  swiperchange: function (e) {
    //console.log(e.detail.current)
    this.setData({
      swiperCurrent: e.detail.current
    })
  },
  tapBanner: function (e) {
    if (e.currentTarget.dataset.id != 0) {
      wx.navigateTo({
        url: "/pages/goods-details/index?id=" + e.currentTarget.dataset.id
      })
    }
  },
  bindTypeTap: function (e) {
    this.setData({
      selectCurrent: e.index
    })
  },
  scroll: function (e) {
    //  console.log(e) ;
    var that = this, scrollTop = that.data.scrollTop;
    that.setData({
      scrollTop: e.detail.scrollTop
    })
    // console.log('e.detail.scrollTop:'+e.detail.scrollTop) ;
    // console.log('scrollTop:'+scrollTop)
  },
  onLoad: function () {
    var that = this
    wx.setNavigationBarTitle({
      title: wx.getStorageSync('mallName')
    });
    var s = '[{"businessId":4036,"dateAdd":"2017-09-15 08:29:50","dateUpdate":"2017-09-20 21:14:36","id":1148,"linkUrl":"","paixu":0,"picUrl":"https://cdn.it120.cc/apifactory/2017/09/15/145c582252a7a20f21ad9a025ae8c9be.png","remark":"","status":0,"statusStr":"显示","title":"1","type":"1","userId":951},{"businessId":0,"dateAdd":"2017-09-15 08:37:04","dateUpdate":"2017-09-15 08:37:25","id":1150,"linkUrl":"","paixu":2,"picUrl":"https://cdn.it120.cc/apifactory/2017/09/15/73560c511f554eb4afd1dcade9d8ef67.png","remark":"","status":0,"statusStr":"显示","title":"3","type":"1","userId":951},{"businessId":3776,"dateAdd":"2017-09-15 08:34:33","dateUpdate":"2017-09-20 21:15:54","id":1149,"linkUrl":"","paixu":3,"picUrl":"https://cdn.it120.cc/apifactory/2017/09/15/5acdd8f65ec85b413ee642dda795d835.png","remark":"","status":0,"statusStr":"显示","title":"2","type":"1","userId":951}]';
    var bannerData = JSON.parse(s);
    that.setData({
      banners: bannerData
    });
    
  }
})
