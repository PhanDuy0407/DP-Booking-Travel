<template>
  <div>
    <!-- <v-card v-if="authentication == true"> -->
      <v-card v-if="checkAuthentication() == true">
      <v-card-title class="justify-center">Thông tin người dùng</v-card-title>
      <v-card-text>
        <v-row>
          <v-col xl="5">
            <v-img
              src="https://cdn.pixabay.com/photo/2021/01/30/20/04/sheltie-5965187_1280.jpg"
              width="300px"
              gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
              height="300px"
              class="elevation-2"
              style="border-radius: 100%; margin: auto"
            ></v-img>
          </v-col>
          <v-col>
            <v-row>
              <v-col lg="4">Họ và tên</v-col>
              <v-col>{{ user.fullname }}</v-col>
            </v-row>
            <v-row>
              <v-col lg="4">Ngày sinh</v-col>
              <v-col>{{ user.dob }}</v-col>
            </v-row>
            <v-row>
              <v-col lg="4">Giới tính</v-col>
              <v-col>{{ user.gender }}</v-col>
            </v-row>
            <v-row>
              <v-col lg="4">Email</v-col>
              <v-col>{{ user.email }}</v-col>
            </v-row>
            <v-row>
              <v-col lg="4">Địa chỉ</v-col>
              <v-col>{{ user.address }}</v-col>
            </v-row>
          </v-col>
        </v-row>
        <v-row>
          <h5 class="text-center black--text">Lịch sử đặt tour</h5>
          <v-data-table
            :headers="headers"
            :items="getOrderHistory"
            :items-per-page="5"
            class="elevation-1"
          ></v-data-table>
        </v-row>
      </v-card-text>
      <v-card-actions class="justify-right">
        <v-col class="text-right"
          ><v-btn color="primary" @click="logOut">Đăng xuất</v-btn></v-col
        >
      </v-card-actions>
    </v-card>
    <v-card v-if="checkAuthentication() == false">
      <v-card-title class="justify-center">Bạn chưa đăng nhập</v-card-title>
    </v-card>
    <!-- <v-row>
      <v-col cols="12" lg="12" xl="4">
        <div>
          <div>
            <div class="my-2">
              <h2 class="text-h4 font-weight-bold">Our Authors</h2>
            </div>

            <v-row>
              <v-col cols="12" md="6" v-for="i in 8" :key="i">
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="50"
                  close-delay="50"
                >
                  <div>
                    <v-card
                      flat
                      :color="hover ? 'white' : 'transparent'"
                      :elevation="hover ? 12 : 0"
                      hover
                    >
                      <v-img
                        src="https://cdn.pixabay.com/photo/2021/01/30/20/04/sheltie-5965187_1280.jpg"
                        :aspect-ratio="16 / 9"
                        gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                        height="300px"
                        class="elevation-2"
                        style="border-radius: 16px"
                      ></v-img>

                      <v-card-text class="text-center">
                        <v-avatar color="accent" size="86" class="authors">
                          <v-icon dark size="64">mdi-feather</v-icon>
                        </v-avatar>

                        <div
                          class="text-h5 font-weight-bold primary--text pt-4"
                        >
                          Yan Lee
                        </div>

                        <div class="text-body-1 py-4">
                          Ultrices sagittis orci a scelerisque. Massa placerat
                          duis ultricies lacus sed turpis tincidunt id.
                        </div>

                        <div>
                          <v-btn icon>
                            <v-icon>mdi-facebook</v-icon>
                          </v-btn>

                          <v-btn icon>
                            <v-icon>mdi-twitter</v-icon>
                          </v-btn>

                          <v-btn icon>
                            <v-icon>mdi-youtube</v-icon>
                          </v-btn>

                          <v-btn icon>
                            <v-icon>mdi-instagram</v-icon>
                          </v-btn>
                        </div>
                      </v-card-text>
                    </v-card>
                  </div>
                </v-hover>
              </v-col>
            </v-row>
          </div>
        </div>
      </v-col>

      <v-col>
        <div>
          
        </div>
      </v-col>
    </v-row> -->
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  name: "Profile",
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
  data() {
    return {
      headers: [
        {
          text: "Mã đơn",
          align: "start",
          sortable: false,
          value: "id",
        },
        { text: "Tên tour", value: "tourName" },
        { text: "Người lớn(SL)", value: "adultCount" },
        { text: "Trẻ em(SL)", value: "childrenCount" },
        { text: "Trẻ nhỏ(SL)", value: "kidCount" },
        { text: "Em bé(SL)", value: "babyCount" },
        { text: "Tổng tiền", value: "sumPrice" },
      ],
    };
  },
  computed: {
    ...mapGetters({
      authentication: "user/getAuthentication",
      user: "user/getUser",
      getOrderHistory: "order/getOrderHistory",
    }),
  },
  created() {
    if (this.checkAuthentication() == true) {
      this.getUser(localStorage.getItem("username"));
      this.actionOrderListHistory(localStorage.getItem("userId"));
    } else {
      this.$router.push("Login")
    }
  },
  methods: {
    ...mapActions({
      getUser: "user/getUser",
      // setAuthentication: "user/setAuthentication",
      actionOrderListHistory: "order/actionOrderListHistory",
    }),
    logOut() {
      localStorage.removeItem("jwt");
      localStorage.removeItem("username");
      localStorage.removeItem("userId");
      // this.setAuthentication(false);
      this.$router.go()
    },
    checkAuthentication() {
      if(localStorage.getItem("username") === null){
        return false;
      }
      return true
    }
  },
};
</script>

<style lang="scss" scoped>
.authors {
  position: relative;
  top: -50px;
  margin-bottom: -50px;
}
</style>
