import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    model:'history',
    routes: [
        {
            path: '/register',
            name: 'register',
            component: () => import(/* webpackChunkName: "register" */ '../components/page/Register.vue'),
            meta: { title: '注册' }
        },
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/master',
            redirect: '/masterpage'
        },
        {
            path: '/nurse',
            redirect: '/nursepage'
        },
        {
            path: '/outdoctor',
            redirect: '/outdoctorpage'
        },
        {
            path: '/innerdoctor',
            redirect: '/innerdoctorpage'
        },
        {
            path: '/patient',
            redirect: '/patientpage'
        },
        /* 院长 */
        {
            path: '/master',
            name: 'masterpage',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/master/master.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/masterpage',
                    name: 'masterpage',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/master/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/masterattendance',
                    name: 'mastertattendance',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/master/BaseTable.vue'),
                    meta: { title: '考勤管理' }
                },
                {
                    path: '/masterdrug',
                    name: 'masterdrug',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/master/DrugTable.vue'),
                    meta: { title: '药品管理' }
                },
                {
                    path: '/masterincome',
                    name: 'masterincome',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/master/IncomeTable.vue'),
                    meta: { title: '收入管理' }
                },
            ]
        },
        /* 护士 */
        {
            path: '/nurse',
            name: 'nursepage',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/nurse/nurse.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/nursepage',
                    name: 'nursepage',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/nurse/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/nursingtable',
                    name: 'nursingtable',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/nurse/NursingTable.vue'),
                    meta: { title: '病人护理' }
                },
            ]
        },
        /* 门诊医生 */
        {
            path: '/outdoctor',
            name: 'outdoctorpage',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/outdoctor/outdoctor.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/outdoctorpage',
                    name: 'outdoctorpage',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/outdoctor/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/outdoctorqueue',
                    name: 'outdoctorqueue',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/outdoctor/QueueTable.vue'),
                    meta: { title: '挂号队列' }
                },
            ]
        },
        /* 住院医生 */
        {
            path: '/innerdoctor',
            name: 'innerdoctorpage',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/innerdoctor/innerdoctor.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/innerdoctorpage',
                    name: 'innerdoctorpage',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/innerdoctor/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/innerdoctorhospital',
                    name: 'innerdoctorhospital',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/innerdoctor/HospitalTable.vue'),
                    meta: { title: '住院申请' }
                }
            ]
        },
        /* 病人用户 */
        {
            path: '/patient',
            name: 'patientpage',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/patient/patient.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/patientpage',
                    name: 'patientpage',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/patient/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
				    path: '/patientregistration',
				    name: 'patientregistration',
				    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/patient/PatientTable.vue'),
				    meta: { title: '挂号' }
				},
				{
				    path: '/patienthospital',
				    name: 'patienthospital',
				    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/patient/ForHospitalTable.vue'),
				    meta: { title: '申请住院' }
				},
				{
				    path: '/payhospital',
				    name: 'payhospital',
				    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/patient/PayTable.vue'),
				    meta: { title: '交住院费' }
				},
				{
				    path: '/PayPrescription',
				    name: 'PayPrescription',
				    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/patient/PayPresTable.vue'),
				    meta: { title: '交药费' }
				},
            ]
        },
        /* 原生 */
        {
            path: '/',
            name: 'page',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/dashboard',
                    name: 'dashboard',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/icon',
                    name: 'icon',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/Icon.vue'),
                    meta: { title: '自定义图标' }
                },
                {
                    path: '/table',
                    name: 'table',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/BaseTable.vue'),
                    meta: { title: '基础表格' }
                },
                {
                    path: '/tabs',
                    name: 'tabs',
                    component: () => import(/* webpackChunkName: "tabs" */ '../components/page/Tabs.vue'),
                    meta: { title: 'tab选项卡' }
                },
                {
                    path: '/form',
                    name: 'form',
                    component: () => import(/* webpackChunkName: "form" */ '../components/page/BaseForm.vue'),
                    meta: { title: '基本表单' }
                },
                {
                    // 富文本编辑器组件
                    path: '/editor',
                    name: 'editor',
                    component: () => import(/* webpackChunkName: "editor" */ '../components/page/VueEditor.vue'),
                    meta: { title: '富文本编辑器' }
                },
                {
                    // markdown组件
                    path: '/markdown',
                    name: 'markdown',
                    component: () => import(/* webpackChunkName: "markdown" */ '../components/page/Markdown.vue'),
                    meta: { title: 'markdown编辑器' }
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    name: 'upload',
                    component: () => import(/* webpackChunkName: "upload" */ '../components/page/Upload.vue'),
                    meta: { title: '文件上传' }
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    name: 'charts',
                    component: () => import(/* webpackChunkName: "chart" */ '../components/page/BaseCharts.vue'),
                    meta: { title: 'schart图表' }
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    name: 'drag',
                    component: () => import(/* webpackChunkName: "drag" */ '../components/page/DragList.vue'),
                    meta: { title: '拖拽列表' }
                },
                {
                    // 拖拽Dialog组件
                    path: '/dialog',
                    name: 'dialog',
                    component: () => import(/* webpackChunkName: "dragdialog" */ '../components/page/DragDialog.vue'),
                    meta: { title: '拖拽弹框' }
                },
                {
                    // 国际化组件
                    path: '/i18n',
                    name: 'i18n',
                    component: () => import(/* webpackChunkName: "i18n" */ '../components/page/I18n.vue'),
                    meta: { title: '国际化' }
                },
                {
                    // 权限页面
                    path: '/permission',
                    name: 'permission',
                    component: () => import(/* webpackChunkName: "permission" */ '../components/page/Permission.vue'),
                    meta: { title: '权限测试', permission: true }
                },
                {
                    path: '/404',
                    name: '404',
                    component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    name: '403',
                    component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                    meta: { title: '403' }
                },
                {
                    path: '/donate',
                    name: 'donate',
                    component: () => import(/* webpackChunkName: "donate" */ '../components/page/Donate.vue'),
                    meta: { title: '支持作者' }
                }
            ]
        },
        {
            path: '/login',
            name: 'login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
