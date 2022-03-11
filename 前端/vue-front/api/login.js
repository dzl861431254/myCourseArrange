import request from '@/utils/request'

export default {
    //登陆
    submitLogin(userInfo) {
        return request({
            url: '/educenter/member/login',
            method: 'post',
            data: userInfo
        })
    },
    //根据token获取用户信息
    getLoginUserInfo() {
        return request({
            url: '/eduservice/user/getUserInfo',
            method: 'get'
        })
    },
    Login(userInfo){
        return request({
            url:'/eduservice/user/user/login',
            method:'post',
            data:userInfo
        })
    }
}