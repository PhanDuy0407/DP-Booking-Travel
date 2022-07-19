<template>
  <div>
    <v-card class="d-flex">
      <v-img
        :src="tour.mainImageUrl"
        :aspect-ratio="16 / 9"
        gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
        height="300px"
        class="elevation-2"
        style="border-radius: 16px"
        width="35%"
      ></v-img>
      <v-card-text>
        <div class="text-h6 font-weight-bold black--text">
          {{ tour.name }}
        </div>
        <div class="text-body-1 pt-5 black--text">
          Khởi hành:
          <span class="font-weight-bold">{{ formatDate(tour.startTime) }}</span>
        </div>
        <div class="text-body-1 black--text">
          Nơi khởi hành:
          <span class="font-weight-bold">{{ tour.startPlaceName }}</span>
        </div>
        <div class="text-body-1 black--text">
          Thời gian: <span class="font-weight-bold">{{ tour.period }}</span>
        </div>
        <div class="text-body-1 black--text">
          <span>Số chỗ còn:</span>
          <span class="font-weight-bold">{{ tour.placeOrderMax }}</span>
        </div>
      </v-card-text>
    </v-card>

    <h2 class="font-weight-bold mt-16">Tổng quan về chuyến đi</h2>
    <v-row>
      <v-col lg="8">
        <h4>Thông tin liên lạc</h4>
        <v-card>
          <v-card-text class="black--text">
            <v-row>
              <v-col>
                <h6>Họ và tên <span class="red--text">*</span></h6>
                <v-text-field
                  outlined
                  dense
                  v-model="order.fullname"
                ></v-text-field>
              </v-col>
              <v-col>
                <h6>Email <span class="red--text">*</span></h6>
                <v-text-field
                  outlined
                  dense
                  v-model="order.email"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <h6>Số điện thoại <span class="red--text">*</span></h6>
                <v-text-field
                  outlined
                  dense
                  v-model="order.phoneNumber"
                ></v-text-field>
              </v-col>
              <v-col>
                <h6>Địa chỉ</h6>
                <v-text-field
                  outlined
                  dense
                  v-model="order.address"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>

        <h4 class="mt-8">Hành khách</h4>
        <v-card>
          <v-card-text class="black--text">
            <v-row>
              <v-col>
                <h6>
                  Người lớn (Từ 12 tuổi trở lên) <span class="red--text"></span>
                </h6>
                <v-text-field
                  outlined
                  dense
                  type="number"
                  v-model="order.adultCount"
                ></v-text-field>
              </v-col>
              <v-col>
                <h6>Trẻ em (Từ 5 - 11 tuổi) <span class="red--text"></span></h6>
                <v-text-field
                  outlined
                  dense
                  type="number"
                  v-model="order.childrenCount"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <h6>Trẻ nhỏ (Từ 2 - 4 tuổi) <span class="red--text"></span></h6>
                <v-text-field
                  outlined
                  dense
                  type="number"
                  v-model="order.kidCount"
                ></v-text-field>
              </v-col>
              <v-col>
                <h6>Em bé (Dưới 2 tuổi) <span class="red--text"></span></h6>
                <v-text-field
                  outlined
                  dense
                  type="number"
                  v-model="order.babyCount"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col>
        <h4>Tóm tắt chuyến đi</h4>
        <v-card>
          <v-card-text class="black--text">
            <h6>Tour trọn gói</h6>
            <v-row>
              <v-col lg="4">
                <v-img
                  :src="tour.mainImageUrl"
                  :aspect-ratio="16 / 9"
                  gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                  height="70px"
                  class="elevation-2"
                  style="border-radius: 16px"
                ></v-img>
              </v-col>
              <v-col>
                <div class="font-weight-bold black--text">
                  {{ tour.name }}
                </div>
              </v-col>
            </v-row>
            <div class="d-flex mt-4">
              <v-icon>mdi-calendar</v-icon>
              <div class="ml-2">
                <div class="black--text">Bắt đầu chuyến đi</div>
                <div class="black--text font-weight-bold">
                  {{ formatDate(tour.startTime) }}
                </div>
              </div>
            </div>

            <div class="d-flex mt-4">
              <v-icon>mdi-calendar</v-icon>
              <div class="ml-2">
                <div class="black--text">Kết thúc chuyến đi</div>
                <div class="black--text font-weight-bold">
                  {{ endTime }}
                </div>
              </div>
            </div>

            <div class="d-flex justify-space-between mt-6">
              <p class="font-weight-bold">Hành khách</p>
              <p class="font-weight-bold">2 người</p>
            </div>
            <div class="d-flex justify-space-between">
              <p>Người lớn</p>
              <p class="font-weight-bold">
                {{ order.adultCount }} x
                {{ formatCurrency(tour.tourPrice.adult) }}
              </p>
            </div>
            <div class="d-flex justify-space-between">
              <p>Trẻ em</p>
              <p class="font-weight-bold">
                {{ order.childrenCount }} x
                {{ formatCurrency(tour.tourPrice.children) }}
              </p>
            </div>
            <div class="d-flex justify-space-between">
              <p>Trẻ nhỏ</p>
              <p class="font-weight-bold">
                {{ order.kidCount }} x {{ formatCurrency(tour.tourPrice.kid) }}
              </p>
            </div>
            <div class="d-flex justify-space-between">
              <p>Em bé</p>
              <p class="font-weight-bold">
                {{ order.babyCount }} x
                {{ formatCurrency(tour.tourPrice.baby) }}
              </p>
            </div>
            <div class="d-flex justify-space-between">
              <p>Phụ thu phòng riêng</p>
              <p class="font-weight-bold">
                {{ formatCurrency(tour.tourPrice.surcharge) }}
              </p>
            </div>
            <v-divider></v-divider>
            <div class="d-flex justify-space-between">
              <p class="text-h6">Tổng cộng</p>
              <p class="text-h6 red--text">{{formatCurrency(sumPrice)}}</p>
            </div>
            <v-btn color="error" width="100%" large @click="bookTour">Đặt ngay</v-btn>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <!-- <v-row>
      <v-col cols="12" lg="12" xl="8">
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
          <siderbar />
        </div>
      </v-col>
    </v-row> -->
  </div>
</template>

<script>
import moment from "moment";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "Order",
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
  data() {
    return {
      order: {
        userId: localStorage.getItem("userId"),
        fullname: "",
        email: "",
        phoneNumber: "",
        address: "",
        adultCount: 0,
        childrenCount: 0,
        kidCount: 0,
        babyCount: 0,
        
        sumPrice: this.sumPrice
      },
    };
  },
  computed: {
    ...mapGetters({
      tour: "tourList/getTour",
    }),
    endTime() {
      return moment(this.tour.startTime)
        .add(this.tour.period, "days")
        .format("DD-MM-yyyy");
    },
    sumPrice() {
      return (
        this.order.adultCount * this.tour.tourPrice.adult +
        this.order.childrenCount * this.tour.tourPrice.children +
        this.order.kidCount * this.tour.tourPrice.kid +
        this.order.babyCount * this.tour.tourPrice.baby
      );
    },
  },
  destroyed() {
    document.title = "DP Travel";
  },

  created() {
    this.getTour(this.$route.params.id);
    document.title = "Đặt tour";
  },
  methods: {
    ...mapActions({
      getTour: "tourList/getTour",
      orderTour: "order/bookTour"
    }),

    async bookTour(){
      this.order = {
        ...this.order,
        priceId: this.tour.priceId,
        sumPrice: this.sumPrice
      }
      console.log(this.order)
      const res = await this.orderTour(this.order);
      if(res.status == 201) {
        alert("Đặt tour thành công")
      }
    },

    formatDate(strDate) {
      const date = new Date(strDate);
      const yyyy = date.getFullYear();
      let mm = date.getMonth() + 1; // Months start at 0!
      let dd = date.getDate();
      if (dd < 10) dd = "0" + dd;
      if (mm < 10) mm = "0" + mm;
      return dd + "-" + mm + "-" + yyyy;
    },
    formatCurrency(currency) {
      return new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
      }).format(currency);
    },
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
