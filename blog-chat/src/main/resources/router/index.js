import {createRouter,createWebHistory} from "vue-router";

const router=createRouter({
    history:createWebHistory(import.meta.env.BASE_URL),
    routes:[
        {
            path:'/',
            name:'login',
            component:()=>import('@/views/WelcomeView.vue'),
            children:[]

        }
    ]
})

export default router
