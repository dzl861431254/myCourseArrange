<template>
  <div class="main" style="width:450px">
    <div class="title" >
      <a class="active" href="/index">登录</a>
      <span>·</span>
      <a href="/studentRegister">学生注册</a>
      <span>·</span>
      <a href="/teacherRegister">教师注册</a>
    </div>
    <div class="sign-up-container">
      <el-form ref="userForm" :model="user">
        <el-form-item class="input-prepend restyle" prop="mobile" :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
          <div >
            <el-input type="text" placeholder="用户名" v-model="user.userName"/>
            <i class="iconfont icon-phone" />
          </div>
        </el-form-item>

        <el-form-item class="input-prepend" prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <div>
            <el-input type="password" placeholder="密码" v-model="user.userPwd"/>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>
        <el-form-item  class="input-prepend" >
       <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
      <el-select v-model="user.role" placeholder="请选择" >       
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
      </el-select>
          </el-form-item>
        <div class="btn">
          <input type="button" class="sign-in-button" value="登录" @click="Login()">
        </div>
      </el-form>
      <!-- 更多登录方式 -->
      <div class="more-sign">
      <h6>社交帐号登录</h6>
        <ul>
          <li><a id="weixin" class="weixin" target="_blank" href="http://qy.free.idcfengye.com/api/ucenter/weixinLogin/login"><i class="iconfont icon-weixin"/></a></li>
          <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>
  </div>

</template>

<script>
  import '~/assets/css/sign.css'
  import '~/assets/css/iconfont.css'
  import cookie from 'js-cookie'
  import loginApi from '@/api/login'

  export default {
    layout: 'sign',
    data () {
      return {
      
         options: [{
          value: '1',
          label: '管理员'
        }, {
          value: '2',
          label: '教授'
        }, {
          value: '3',
          label: '学生'
        }],
        user:{
          userName:'',
          userPwd:'',
          role:''
        },
        loginInfo:{},
        token:{},
        role:''
      }
    },
    methods: {
      //登陆

      Login() {
        //1.调用接口进行登陆,得到tocken字符串
        loginApi.Login(this.user)
                .then(response => {
                  alert(JSON.stringify(this.user))
                               
                  //2.将token字符串放到cookie里
                             //key         value                    作用范围
                             
                   cookie.set('guli_token',response.data.data.token,{domain: 'localhost'})
                    alert(JSON.stringify(cookie.get('guli_token')))
                  //4.调用接口,根据token获取用户信息
                  loginApi.getLoginUserInfo() 
                          .then(response => {
                             this.loginInfo = response.data.data.userInfo
                             this.role=response.data.data.userrole
                                alert(JSON.stringify(this.loginInfo))
                             //获取返回用户信息，放到cookie里面
                             cookie.set('guli_ucenter',this.loginInfo,{domain: 'localhost'})
                               cookie.set('user_role',this.role,{domain: 'localhost'})
                             //跳转页面
                             window.location.href="/student/getClassList"
                          })                  
                })
      }

    }
  }
</script>
<style>
   .el-form-item__error{
    z-index: 9999999;
  }
</style>