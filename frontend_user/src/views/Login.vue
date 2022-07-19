<template>
  <v-row class="d-flex justify-center align-center fill-height" style="min-height: 100vh">
    <v-col cols="12" md="6">
      <v-card class="py-6">
        <v-card-title class="d-flex justify-center">
          <div class="text-h4">
            Login
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
          <div class="text-right">
            <v-btn color="primary" @click="submit">
              Login
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
      password: ''
    }
  },
  methods: {
    ...mapActions({
      setAuthentication: "user/setAuthentication"
    }),
    async submit() {
      const data = await axiosIns.post(
        'http://localhost:8089/api/v1/login',
        {
          "username": this.username,
          "password": this.password
        }
      )
      if (data.status === 200) {
        localStorage.username = this.username
        localStorage.userId = data.data.data.id
        localStorage.jwt = data.data.data.jwt
        this.setAuthentication(true)
        // this.$router.push({ path: '/' })
        this.$router.push({ name: 'Home' })
      }
    }
  }
}
</script>
