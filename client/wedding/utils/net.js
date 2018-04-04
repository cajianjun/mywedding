var rootPath = "http://localhost:8888";
function post(path,data,cb){
	wx.request({
          url: rootPath + path,
          method:'POST',
          header: {
            'content-type': 'application/json'
          },
          data: {
          	token:getApp().globalData.token,
            data:data
          },
          success: (resp) => {
            cb(resp.data.data);
          }
        })
}

function post(path,data,cb){
  wx.request({
          url: rootPath + path,
          method:'GET',
          header: {
            'content-type': 'application/json'
          },
          success: (resp) => {
            cb(resp.data.data);
          }
        })
}

module.exports = {
  post: post,
  get:get
}