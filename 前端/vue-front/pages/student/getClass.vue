<template>
<div>
  <el-table
    :data="mytimetable"
    border
    style="width: 100%">
     
    <el-table-column
      prop="id"
      label=""
      width="180">
    </el-table-column>
    <el-table-column
      prop="monday"
      label="星期一"
      width="180">
    </el-table-column>
    <el-table-column
      prop="tuesday"
      label="星期二">
    </el-table-column>
    <el-table-column
      prop="wednesday"
      label="星期三">
    </el-table-column>
    <el-table-column
      prop="thursday"
      label="星期四">
    </el-table-column>
    <el-table-column
      prop="friday"
      label="星期五">
    </el-table-column>
  </el-table>
    </div>
   
</template>

<script>

import timetable from '@/api/timetable'
 import cookie from 'js-cookie'
  export default {
    data() {
      return {
          timetableList:null,
          size:null,
        mytimetable:null,
        timetableIdList:[
                        ],
        timetable1: [{
          number: '1',
          monday: 'monday',
          tuesday:'tuesday',
          wednesday:'wednesday',
          thursday:'thursday',
          friday:'friday'
        
        }, {
           number: '2',
          monday: 'monday',
          tuesday:'tuesday',
          wednesday:'wednesday',
          thursday:'thursday',
          friday:'friday'
          
        }, {
           number: '3',
          monday: 'monday',
          tuesday:'tuesday',
          wednesday:'wednesday',
          thursday:'thursday',
          friday:'friday',
          
        },{
            number: '4',
          monday: 'monday',
          tuesday:'tuesday',
          wednesday:'wednesday',
          thursday:'thursday',
          friday:'friday'
        },{
            number: '5',
          monday: 'monday',
          tuesday:'tuesday',
          wednesday:'wednesday',
          thursday:'thursday',
          friday:'friday'
        },{
            number: '6',
          monday: 'monday',
          tuesday:'tuesday',
          wednesday:'wednesday',
          thursday:'thursday',
          friday:'friday'
        },{
            number: '7',
          monday: 'monday',
          tuesday:'tuesday',
          wednesday:'wednesday',
          thursday:'thursday',
          friday:'friday'
        },{
            number: '8',
          monday: 'monday',
          tuesday:'tuesday',
          wednesday:'wednesday',
          thursday:'thursday',
          friday:'friday'
        }
        ],
          student:{
           stuId:'',
          stuName:'',
          teamId:''
       },
       teacher:{
          teacherId:'',
          teacherName:'',
          phoneNum:''
       },
       role:'',
       index:'',
      
      
      }
    }
    ,
    created() { //页面渲染之前执行，一般调用methods定义的方法
        //调用
        this.init() 
    }
    ,
    watch:{//路由发生变化就会执行这个方法
        $route(to,from){
            this.init()
        }
    },
    methods:{ 
      getListbyProfessorName(Name) {
           timetable.getTimetableByProfessorName(Name)
                .then(response =>{//请求成功
                    //response接口返回的数据
                    //console.log(response)
                    this.timetableList = response.data.data.result,
                    this.size=response.data.data.size,
                     this.mytimetable=this.timetableList[this.index]
                    //  alert(JSON.stringify(this.timetableList))
                //     for(var i in this.timetableList){
                //     //     alert(JSON.stringify(i))
                //     //    alert(JSON.stringify(this.timetableList[i]))

                //     }
                 }) 
               
       },
       getList(id) {
           timetable.getTimetableByTeamId(id)
                .then(response =>{//请求成功
                    //response接口返回的数据
                    //console.log(response)
                    this.timetableList = response.data.data.result,
                    this.size=response.data.data.size,
                     this.mytimetable=this.timetableList[this.index]
                    //  alert(JSON.stringify(this.timetableList))
                //     for(var i in this.timetableList){
                //     //     alert(JSON.stringify(i))
                //     //    alert(JSON.stringify(this.timetableList[i]))

                //     }
                 }) 
               
       },
         init(){
            //判断路径中是否有id值
        if(this.$route.params &&this.$route.params.id){
               this.role=cookie.get('user_role')
              var info=cookie.get('guli_ucenter')
                  this.index= this.$route.params.id
              if(this.role==3){
                this.student=JSON.parse(info)
                      
                 this.getList(this.student.teamId)
              }else if(this.role==2){
                this.teacher=JSON.parse(info)
                this.getListbyProfessorName(this.teacher.teacherName)
              }
            
        }else{
            //清空讲师表单页面内内容
            
            this.timetable1={}
            
        }

        
        }
    }
      
  }
</script>



