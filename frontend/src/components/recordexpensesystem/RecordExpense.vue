<template>
  <v-container fluid>
    <v-hover v-slot:default="{ hover }">
      <v-card width="600" :elevation="hover ? 12 : 5" style="margin: auto; margin-top: 50px;">
        <v-app-bar dark color="light-blue lighten-1">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>RecordExpense</v-toolbar-title>

          <v-spacer></v-spacer>

          <v-btn icon>
            <v-icon>mdi-dialpad</v-icon>
          </v-btn>
        </v-app-bar>

        <v-container style="margin-top: 50px; padding-bottom: 30px;">
          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select  label="Expense Type"  v-model="RecordExpense.expenseType" :items="ExpenseType" item-text="type" item-value="id" outlined @change="setSelete"></v-select>
            </v-col>
          </v-row>

          <v-row v-if="selete.emp">
            <v-col cols="8" style="margin: auto;">
              <v-select  label="Employee"  v-model="RecordExpense.rec" :items="employee" item-text="fullname" item-value="id" outlined @change="setBudget"></v-select>
            </v-col>
          </v-row>

          <v-row v-if="selete.stu">
            <v-col cols="8" style="margin: auto;">
              <v-select  label="StudentName"  v-model="RecordExpense.student" :items="enrollCourse" item-text="student.fullname" item-value="id" outlined></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field disabled  v-model="RecordExpense.budget"  label="Budget" outlined dense></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field v-model="RecordExpense.date"  label="Date" outlined></v-text-field>
              <!-- disabled -->
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select v-model="RecordExpense.createdBy" :items="employee" item-text="fullname" item-value="id" label="Created by"  outlined></v-select>
          
            </v-col>
          </v-row>

          <v-row>
            <v-btn
              style="margin: auto;"
              large
              color="light-blue lighten-1"
              dark
              @click="snackbar = true"
            >SAVE FROM</v-btn>
          </v-row>
        </v-container>
      </v-card>
    </v-hover>

    <v-row>
      <v-snackbar v-model="snackbar">
        {{ text }}
        <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
      </v-snackbar>
    </v-row>
  </v-container>
</template>
<script>
import http from '../../http-common';
export default {
  name: "RecordExpense",
  data: () => ({
    selete:{
      stu:false,
      emp:false,
    },
    RecordExpense:{
      expenseType:"",
      student:"",
      budget:"",
      rec:"",
      date:"",
      createdBy:""
    },
    snackbar: false,
    text: "OK !,Data has been saved successfully.",
    ExpenseType:[],
    employee:[],
    enrollCourse:[],
  }) ,
  methods: {
    /* eslint-disable no-console */
    reloadPage(){
      window.location.reload(false);
    },
    setSelete(){
      if(this.RecordExpense.expenseType == 2){
        this.selete.emp = true;
        this.selete.stu = false;
      }else if(this.RecordExpense.expenseType == 1){
        this.selete.stu = true;
        this.selete.emp = false;
        this.RecordExpense.budget = "";  
      }
    },
    setBudget(){
      if(this.RecordExpense.expenseType == 2){
        //emp
          for (let elem in this.employee) {
              if(this.employee[elem].id == this.RecordExpense.rec){
                this.RecordExpense.budget = this.employee[elem].position.salary;
                console.log(this.employee[elem].position.salary);
              }
        }
        console.log("emp");

      }
    },
    getNowTime(){
      // const answer = x > 10 ? "greater than 10" : "less than 10";
      var xTime = new Date();
      var FullYear = xTime.getFullYear();
      var Month = (xTime.getMonth()+1) > 9 ? (xTime.getMonth()+1):("0"+(xTime.getMonth()+1));
      var Day = xTime.getDate() > 9 ? xTime.getDate():("0"+xTime.getDate());
      var Hours = xTime.getHours() > 9 ? xTime.getHours():("0"+xTime.getHours());
      var Minutes = xTime.getMinutes() > 9 ? xTime.getMinutes():("0"+xTime.getMinutes());
      var Seconds = xTime.getSeconds() > 9 ? xTime.getSeconds():("0"+xTime.getSeconds());
      this.RecordExpense.date =  FullYear +"-"+Month +"-"+Day+" "+Hours+":"+Minutes+":"+Seconds;
    },
    getExpenseType() {
      http
        .get("/expenseType/")
        .then(response => {
          this.ExpenseType = response.data;
           console.log(this.ExpenseType);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getEnrollCourse() {
      http
        .get("/enrollCourse/")
        .then(response => {
          this.enrollCourse = response.data;
           console.log(this.enrollCourse);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employee = response.data;  
       
          
        })
        .catch(e => {
          console.log(e);
        });
    },
    getStudents() {
      http
        .get("/student/")
        .then(response => {
          this.students = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  },
  created () {
    setInterval(() => this.getNowTime())
  },
  mounted() {
    this.getExpenseType();
    this.getEmployees();
    this.getEnrollCourse();
      
  }
};
</script>
