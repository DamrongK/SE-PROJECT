import Vue from 'vue'
import Router from 'vue-router';

import Home from '../components/HomePages/Home';
import AddEmployee from '../components/EmployeeManagementSystem/AddEmployee';
import AddStudent from '../components/StudentManagemenSystem/AddStudent';
import AddCourse from '../components/CourseManagementSystem/AddCourse';
import CourseRegister from '../components/CourseRegistrationManagementSystem/CourseRegister';
import RecordExpense from '../components/recordexpensesystem/RecordExpense';
import AddReviews from '../components/ReviewsManagementSystem/AddReviews';

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            component: Home
        },
        {
            path: '/AddEmployee',
            component: AddEmployee
        },
        {
            path: '/AddStudent',
            component: AddStudent
        },
        {
            path: '/AddCourse',
            component: AddCourse
        },
        {
            path: '/CourseRegister',
            component: CourseRegister
        },
        {
            path: '/RecordExpense',
            component: RecordExpense
        },
        {
            path: '/AddReviews',
            component: AddReviews
        },

    ]
});