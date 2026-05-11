const base = {
    get() {
        return {
            url : "http://localhost:8080/house/",
            name: "house",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/house/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "房屋出售系统"
        } 
    }
}
export default base
