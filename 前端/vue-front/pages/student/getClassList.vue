<template>

   <div>
        <el-table
      :data="timetableIdList"
      border
      fit
      highlight-current-row>


      <el-table-column prop="id" label="课程表id" width="100" />
    


      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/student/getClass/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">打开课表</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

   </div>
</template>

<script>
 import cookie from 'js-cookie'
import timetable from '@/api/timetable'

  export default {
    data() {
      return {
          timetableList:null,
          size:null,
        mytimetable:null,
        timetableIdList:[
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
       getList(id) {
           timetable.getTimetableByTeamId(id)
                .then(response =>{//请求成功
                    //response接口返回的数据
                    //console.log(response)
                    this.timetableList = response.data.data.result,
                    this.size=response.data.data.size
                    for(var i in this.timetableList){
                    //     alert(JSON.stringify(i))
                    //    alert(JSON.stringify(this.timetableList[i]))
                            this.timetableIdList.push({id:i})

                    }
                    // ,this.mytimetable=this.timetableList[236]
                }) 
       },
         init(){
           this.role=cookie.get('user_role')
              var info=cookie.get('guli_ucenter')
                
              if(this.role==3){
                this.student=JSON.parse(info)
                      
                 this.getList(this.student.teamId)
              }else if(this.role==2){
                this.teacher=JSON.parse(info)
                this.getListByProfessorName(this.teacher.teacherName)
              }
                  

        },
        getListByProfessorName(name) {
           timetable.getTimetableByProfessorName(name)
                .then(response =>{//请求成功
                    //response接口返回的数据
                    //console.log(response)
                    alert(JSON.stringify(this.teacher))
                    this.timetableList = response.data.data.result,
                    this.size=response.data.data.size
                    for(var i in this.timetableList){
                    //     alert(JSON.stringify(i))
                    //    alert(JSON.stringify(this.timetableList[i]))
                            this.timetableIdList.push({id:i})

                    }
                    // ,this.mytimetable=this.timetableList[236]
                }) 
       }
    }
      
  }
</script>



