<template>
  <div class="bg-light min-vh-100 d-flex flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center" style="text-align: center">
        <CCol :md="8">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <CForm>
                  <h1>Đăng nhập</h1>
                  <p class="text-medium-emphasis">
                    Đăng nhập vào tài khoản của bạn
                  </p>
                  <CInputGroup class="mb-3">
                    <CInputGroupText>
                      <CIcon icon="cil-user" />
                    </CInputGroupText>
                    <CFormInput
                      placeholder="Tên đăng nhập"
                      autocomplete="username"
                      v-model="username"
                    />
                  </CInputGroup>
                  <CInputGroup class="mb-4">
                    <CInputGroupText>
                      <CIcon icon="cil-lock-locked" />
                    </CInputGroupText>
                    <CFormInput
                      type="password"
                      placeholder="Mật khẩu"
                      autocomplete="current-password"
                      v-model="password"
                    />
                  </CInputGroup>
                  <CRow>
                    <CCol :xs="12">
                      <CButton color="primary" class="px-4" @click="submit">
                        Đăng nhập
                      </CButton>
                    </CCol>
                  </CRow>
                </CForm>
              </CCardBody>
            </CCard>
            <!-- <CCard class="text-white bg-primary py-5" style="width: 44%">
              <CCardBody class="text-center">
                <div>
                  <h2>Sign up</h2>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit,
                    sed do eiusmod tempor incididunt ut labore et dolore magna
                    aliqua.
                  </p>
                  <CButton color="light" variant="outline" class="mt-3">
                    Đăng ký!
                  </CButton>
                </div>
              </CCardBody>
            </CCard> -->
          </CCardGroup>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
import axiosIns from 'axios'
import { mapActions } from 'vuex'
export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: '',
    }
  },
  methods: {
    ...mapActions({
      setAuthentication: 'user/setAuthentication',
    }),
    async submit() {
      const data = await axiosIns.post(
        'http://localhost:8089/api/v1/login-admin',
        {
          username: this.username,
          password: this.password,
        },
      )
      if (data.status === 200) {
        localStorage.username = this.username
        localStorage.userId = data.data.data.id
        localStorage.jwt = data.data.data.jwt
        this.setAuthentication(true)
        // this.$router.push({ path: '/' })
        this.$router.push({ name: 'Tour' })
      }
    },
  },
}
</script>
