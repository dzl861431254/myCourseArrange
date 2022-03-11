import request from '@/utils/request'
export default {
    create(){
        return request({
            url:`/timetable/create`,
            method:'get'
        })  
    },


    //1 讲师列表（条件查询分页）
    //current当前页 limit每页记录数 teacherQuery条件对象
    getTimetable() {
        return request({
            url: `/timetable/test`,
            method: 'get',
          })
    },
    //1 课程表列表（条件查询分页）
    //current当前页 limit每页记录数 teacherQuery条件对象
    pageListTimetable(current,limit) {
        return request({
            url: `/timetable/${current}/${limit}`,
            method: 'get',
          })
    },
     //删除课程表
     deleteTimetableId(id=Number) {
        return request({
            url: `/timetable/${id}`,
            method: 'delete'
          })
    },
    //通过id获取timetable
    getTimetableById(id=Number){
        return request({
            url: `/timetable/getTimetableInfo/${id}`,
            method: 'get',
          })
    },
    //添加教室到timetable中
    addRoom(roomId=Number){
        return request({
            url:`/timetable/addRoom/${roomId}`,
            method:`get`
        })
    },
    //添加教授到timetable中
    addProfessor(professorId=Number){
        return request({
            url:`/timetable/addProfessor/${professorId}`,
            method:`get`

        })
    },
    //添加课程到timetable中
    addCourse(courseId=Number){
        return request({
            url:`/timetable/addCourse/${courseId}`,
            method:`get`

        })
    },
    //添加班级到timetable中
    addTeam(teamId=Number){
        return request({
            url:`/timetable/addTeam/${teamId}`,
            method:`get`,
        })
    },
    //添加课程的教授列表
    setProfessorCourse(courseId=Number,professorId=Number){
        return request({
            url:`/timetable/setProfessorCourse/${courseId}/${professorId}`,
            method:`get`
            

        })
    }
    ,
    //添加班级的课程列表
    setTeamCourse(teamId=Number,courseId=Number){
        return request({
            url:`/timetable/setTeamCourse/${teamId}/${courseId}`,
            method:`get`
            

        })
    },
    //返回课程列表
    getCourses(){
        return request({
            url:`/timetable/getCourses`,
            method:`get`,

        })
    },
    //返回班级列表
    getTeams(){
        return request({
            url:`/timetable/getTeams`,
            method:`get`,

        })
    },
    //返回教授列表
    getProfessors(){
        return request({
            url:`/timetable/getProfessors`,
            method:`get`,

        })
    },
    //返回教室列表
    getRooms(){
        return request({
            url:`/timetable/getRooms`,
            method:`get`,

        })
    },
    //返回课程的教授列表
    getProfessorCourse(courseid=Number){
        return request({
            url:`/timetable/getProfessorCourse/${courseid}`,
            method:`get`,

        })
    },
    //返回班级的课程列表
    getTeamCourse(teamid=Number){
        return request({
            url:`/timetable/getTeamCourse/${teamid}`,
            method:`get`,

        })
    },
    deleteRoom(roomid=Number){
        return request({
            url:`/timetable/deleteRoom/${roomid}`,
            method:`get`,

        })
    },
    deleteProfessor(professorid=Number){
        return request({
            url:`/timetable/deleteProfessor/${professorid}`,
            method:`get`,

        })
    },
    deleteCourse(courseid=Number){
        return request({
            url:`/timetable/deleteCourse/${courseid}`,
            method:`get`,

        })
    },
    deleteTeam(teamid=Number){
        return request({
            url:`/timetable/deleteTeam/${teamid}`,
            method:`get`,

        })
    },
    getBestClass(){
        return request({
            url:`/timetable/bestclass`,
            method:`get`,

        }) 
    },
    getTimetableByTeamId(teamId=Number){
        return request({
            url:`/timetable/getTimetableByTeamId/${teamId}`,
            method:`get`
        })
    },
    getTimetableByProfessorName(professorName){
        return request({
            url:`/timetable/getTimetableByProfessorName/${professorName}`,
            method:`get`
        })
    }
}
