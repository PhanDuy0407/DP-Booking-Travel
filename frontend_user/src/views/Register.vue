<template>
  <v-row class="d-flex justify-center align-center fill-height" style="min-height: 100vh">
    <v-col cols="12" md="6">
      <v-card class="py-6">
        <v-card-title class="d-flex justify-center">
          <div class="text-h4">
            Register
          </div>
        </v-card-title>
        <v-card-text>
          <v-text-field
              label="Username"
              outlined
              v-model="username"
          ></v-text-field>
          <v-text-field
              label="Password"
              outlined
              v-model="password"
          ></v-text-field>
          <v-text-field
              label="Họ và tên"
              outlined
              v-model="fullname"
          ></v-text-field>
          <v-text-field
              label="Email"
              outlined
              v-model="email"
          ></v-text-field>
          <v-text-field
              label="Địa chỉ"
              outlined
              v-model="address"
          ></v-text-field>
          <!-- <v-text-field
              label="Ngày Sinh"
              outlined
              v-model="dob"
          ></v-text-field> -->
          <v-menu
            :close-on-content-click="true"
            transition="scale-transition"
            offset-y
            min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="dob"
                label="Ngày Sinh"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
              v-model="dob"
            ></v-date-picker>
          </v-menu>
          <!-- <v-text-field
              label="Giới tính"
              outlined
              v-model="gender"
          ></v-text-field> -->
          <v-select
            v-model="gender"
            :items="items"
            label="Giới tính"
            outlined
          ></v-select>
          <div class="text-right">
            <v-btn color="primary" @click="submit">
              Register
            </v-btn>
          </div>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import axiosIns from 'axios'
import {mapActions, mapGetters} from 'vuex'
export default {
  data() {
    return {
      username: '',
      password: '',
      fullname: '',
      email:'',
      address:'',
      dob: '',
      gender:'',
      items: ['Nam', 'Nữ'],
    }
  },
  methods: {
    ...mapActions({
      // setAuthentication: "user/setAuthentication"
    }),
    async submit() {
      const [year, month, day] = this.dob.split('-')
      const dateFormat = `${day}/${month}/${year}`
      
      console.log(
        {
          "username": this.username,
          "password": this.password,
          "fullname": this.fullname,
          "email": this.email,
          "address": this.address,
          "dob": dateFormat,
          "gender": this.gender,
        }
      )
      const data = await axiosIns.post(
        'http://localhost:8089/api/v1/register',
        {
          "username": this.username,
          "password": this.password,
          "fullname": this.fullname,
          "email": this.email,
          "address": this.address,
          "dob": dateFormat,
          "gender": this.gender,
        }
      )
      if (data.status === 200) {
        localStorage.username = this.username
        localStorage.userId = data.data.data.id
        localStorage.jwt = data.data.data.jwt
        this.$router.push({ name: 'Home' })
      }
    }
  }
}
</script>
