<template>
  <div>
    <div class="background-img">
      <!-- <b-card class="menu-tab"> -->
      <!-- <b-tabs>
          <b-tab title="Tour du lịch">
            aaaaaaaaaaaaaa
          </b-tab>
          <b-tab title="Tour du lịch">
            aaaaaaaaaaaaaa
          </b-tab>
          <b-tab title="Tour du lịch">
            aaaaaaaaaaaaaa
          </b-tab>
          <b-tab title="Tour du lịch">
            aaaaaaaaaaaaaa
          </b-tab>
        </b-tabs> -->
      <v-card class="menu-tab">
        <v-tabs
          v-model="tab"
          centered
          color="white"
          background-color="rgba(45,66,113,.8)"
          icons-and-text
        >
          <v-tabs-slider></v-tabs-slider>

          <v-tab href="#tab-1" class="menu-tab-item"> Tour du lịch </v-tab>

          <v-tab href="#tab-2" class="menu-tab-item"> Khách sạn </v-tab>

          <v-tab href="#tab-3" class="menu-tab-item"> Vé máy bay </v-tab>

          <v-tab href="#tab-4" class="menu-tab-item"> Tra cứu booking </v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab">
          <v-tab-item value="tab-1">
            <v-card flat>
              <v-card-title class="justify-center"
                >Tìm kiếm tour phù hợp với bạn</v-card-title
              >
              <v-card-text class="d-flex justify-space-between align-center">
                <div class="filter-box">
                  <v-autocomplete
                    prepend-icon="mdi-map-marker-outline"
                    label="Điểm đi"
                    :items="places"
                    v-model="startPlace"
                    item-value="id"
                    item-text="name"
                  >
                  </v-autocomplete>
                </div>

                <v-icon large>mdi-swap-horizontal</v-icon>

                <div class="filter-box ml-4">
                  <v-autocomplete
                    prepend-icon="mdi-map-marker-outline"
                    label="Điểm đến"
                    :items="places"
                    v-model="endPlace"
                    item-value="id"
                    item-text="name"
                  >
                  </v-autocomplete>
                </div>

                <div class="filter-box">
                  <!-- <v-text-field type="date" label="Ngày đi"></v-text-field> -->
                  <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    :return-value.sync="startDate"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="startDate"
                        label="Ngày đi"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="startDate" no-title scrollable>
                      <v-spacer></v-spacer>
                      <v-btn text color="primary" @click="menu = false">
                        Cancel
                      </v-btn>
                      <v-btn
                        text
                        color="primary"
                        @click="$refs.menu.save(startDate)"
                      >
                        OK
                      </v-btn>
                    </v-date-picker>
                  </v-menu>
                </div>

                <div class="filter-box">
                  <v-select
                    prepend-icon="mdi-calendar"
                    label="Số ngày"
                    :items="periods"
                    v-model="numOfDay"
                  >
                  </v-select>
                </div>
                <v-btn
                  large
                  color="#2d4271"
                  @click="clickSearch"
                  to="/category"
                >
                  <v-icon large color="yellow">mdi-arrow-right</v-icon>
                </v-btn>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item value="tab-2">
            <v-card flat>
              <v-card-text> Tính năng đang phát triển </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
      <!-- </b-card> -->
    </div>

    <v-row class="mt-16">
      <v-col cols="12" lg="12">
        <div>
          <div class="pt-16 mt-16">
            <h2 class="text-h4 font-weight-bold pb-4 text-center">
              Tour ưu đãi
            </h2>

            <v-row>
              <v-col v-for="(item, i) in toursDiscount" :key="i" cols="12" lg="4" md="6">
                <v-hover
                  v-slot:default="{ hover }"
                  close-delay="50"
                  open-delay="50"
                >
                  <div>
                    <v-card
                      :color="hover ? 'white' : 'transparent'"
                      :elevation="hover ? 12 : 0"
                      flat
                      hover
                      :to="{ name: 'Detail', params: { id: item.id } }"
                    >
                      <v-img
                        :aspect-ratio="16 / 9"
                        class="elevation-2"
                        gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                        height="200px"
                        :src="item.mainImageUrl"
                        style="border-radius: 16px"
                      >
                        <!-- <v-card-text>
                          <v-btn color="accent" to="category">TIPS</v-btn>
                        </v-card-text> -->
                      </v-img>

                      <v-card-text>
                        <div class="text-h6 font-weight-bold black--text">
                          {{ item.name }}
                        </div>

                        <div class="text-body-1 pt-5 black--text">
                          Nơi khởi hành: {{ item.startPlaceName }}
                        </div>
                        <div class="text-body-1 black--text">
                          Giá:
                          <span class="text-decoration-line-through"
                            >{{ formatCurrency(item.price) }}</span
                          >
                        </div>
                        <div class="text-body-1 red--text font-weight-bold">
                          {{ formatCurrency(item.price-item.discount) }}
                        </div>
                        <div class="text-body-1 text-center py-5 indigo--text">
                          Còn: {{calRemainingTime(item.endDateDiscount)}}
                        </div>
                        <div class="text-body-1 text-right black--text">
                          <span class="text-decoration-underline"
                            >Số chỗ còn:</span
                          >
                          <span class="red--text">{{item.placeOrderMax}}</span>
                        </div>

                        <!-- <div class="d-flex align-center">
                          <v-avatar color="accent" size="36">
                            <v-icon dark>mdi-feather</v-icon>
                          </v-avatar>

                          <div class="pl-2">Yan Lee · 22 July 2019</div>
                        </div> -->
                      </v-card-text>
                    </v-card>
                  </div>
                </v-hover>
              </v-col>
            </v-row>
          </div>

          <div class="pt-16">
            <!-- <h2 class="text-h4 font-weight-bold pb-4">Featured</h2>

            <v-row>
              <v-col v-for="i in 3" :key="i" cols="6" lg="4">
                <v-card dark flat>
                  <v-img
                    :aspect-ratio="16 / 9"
                    class="elevation-2 fill-height"
                    gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                    height="600px"
                    src="https://cdn.pixabay.com/photo/2019/10/29/14/46/landscape-4587079_1280.jpg"
                  >
                    <div
                      class="
                        d-flex
                        flex-column
                        justify-space-between
                        fill-height
                      "
                    >
                      <v-card-text>
                        <v-btn color="accent">ANIMALS</v-btn>
                      </v-card-text>

                      <v-card-text>
                        <div
                          class="text-h5 py-3 font-weight-bold"
                          style="line-height: 1.2"
                        >
                          15 things I have always wondered about birds
                        </div>

                        <div class="d-flex align-center">
                          <v-avatar color="accent" size="36">
                            <v-icon dark>mdi-feather</v-icon>
                          </v-avatar>

                          <div class="pl-2">Yan Lee · 03 Jan 2019</div>
                        </div>
                      </v-card-text>
                    </div>
                  </v-img>
                </v-card>
              </v-col>
            </v-row> -->
            <h2 class="text-h4 font-weight-bold pb-4 text-center">
              Tour ưa thích
            </h2>

            <v-row>
              <v-col v-for="i in 6" :key="i" cols="12" lg="4" md="6">
                <v-hover
                  v-slot:default="{ hover }"
                  close-delay="50"
                  open-delay="50"
                >
                  <div>
                    <v-card
                      :color="hover ? 'white' : 'transparent'"
                      :elevation="hover ? 12 : 0"
                      flat
                      hover
                      to="/detail"
                    >
                      <v-img
                        :aspect-ratio="16 / 9"
                        class="elevation-2"
                        gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                        height="200px"
                        src="https://cdn.pixabay.com/photo/2020/12/23/14/41/forest-5855196_1280.jpg"
                        style="border-radius: 16px"
                      >
                        <v-card-text>
                          <v-btn color="accent" to="category">TIPS</v-btn>
                        </v-card-text>
                      </v-img>

                      <v-card-text>
                        <div class="text-h5 font-weight-bold black--text">
                          Bangkok - Pattaya (Khách sạn 4*, tặng Show Alcazar và
                          Buffet tại BaiYoke Sky)
                        </div>

                        <div class="text-body-1 pt-5 black--text">
                          Nơi khởi hành: TP. Hồ Chí Minh
                        </div>
                        <div class="text-body-1 black--text">
                          Giá:
                          <span class="text-decoration-line-through"
                            >9.000.000đ</span
                          >
                        </div>
                        <div class="text-body-1 red--text font-weight-bold">
                          8.000.000đ
                        </div>
                        <div class="text-body-1 text-center py-5 indigo--text">
                          Còn: 00 ngày 09:09:00
                        </div>
                        <div class="text-body-1 text-right black--text">
                          <span class="text-decoration-underline"
                            >Số chỗ còn:</span
                          >
                          <span class="red--text"> 3</span>
                        </div>

                        <!-- <div class="d-flex align-center">
                          <v-avatar color="accent" size="36">
                            <v-icon dark>mdi-feather</v-icon>
                          </v-avatar>

                          <div class="pl-2">Yan Lee · 22 July 2019</div>
                        </div> -->
                      </v-card-text>
                    </v-card>
                  </div>
                </v-hover>
              </v-col>
            </v-row>
          </div>

        </div>
      </v-col>

      <!-- <v-col>
        <div class="pt-16">
          <siderbar />
        </div>
      </v-col> -->
    </v-row>
  </div>
</template>

<script>
import moment from "moment";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "Home",
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
  data() {
    return {
      tab: null,
      menu: false,
      // places: [
      //   { value: 1, text: "Hà Nội" },
      //   { value: 2, text: "TPHCM" },
      // ],
      periods: [
        { value: 1, text: "1-3 ngày" },
        { value: 2, text: "4-7 ngày" },
        { value: 3, text: "8-14 ngày" },
        { value: 4, text: "Trên 14 ngày" },
      ],
      startPlace: "",
      endPlace: "",
      startDate: "",
      numOfDay: null,
    };
  },

  computed: {
    ...mapGetters({
      places: "place/getPlaces",
      toursDiscount: "tourList/getToursDiscount"
    }),
    priceDiscount(){
      
    }
  },

  created() {
    this.getPlaces();
    this.getToursDiscount();
  },

  methods: {
    ...mapActions({
      setObjSearch: "tourList/setObjSearch",
      getPlaces: "place/getAll",
      getToursDiscount: "tourList/getToursDiscount"
    }),
    clickSearch() {
      var fromPeriod;
      var toPeriod;
      switch (this.numOfDay) {
        case 1:
          fromPeriod = 1;
          toPeriod = 3;
          break;
        case 2:
          fromPeriod = 4;
          toPeriod = 7;
          break;
        case 3:
          fromPeriod = 8;
          toPeriod = 14;
          break;
        case 4:
          fromPeriod = 15;
          toPeriod = "";
          break;

        default:
          fromPeriod = "";
          toPeriod = "";
          break;
      }
      const obj = {
        startPlaceId: this.startPlace,
        endPlaceId: this.endPlace,
        startTime: this.startDate,
        period: this.numOfDay,
        fromPeriod: fromPeriod,
        toPeriod: toPeriod,
        fromPrice: "",
        toPrice: "",
      };
      console.log(obj);
      this.setObjSearch(obj);
    },

    calRemainingTime(date){
      var a = moment();
      var b = moment(new Date(date))
      var diff = moment.duration(b.diff(a))
      var day = diff.days();
      
      return day + " ngày " + diff.hours()+ ":" + diff.minutes() + ":" + diff.seconds()
    },

    formatCurrency(currency){
      return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(currency)
    }
  },
};
</script>
<style>
.menu-tab {
  width: 90%;
  position: absolute;
  top: calc(100% - 72px);

  color: white !important;
}
.menu-tab-item {
  color: white !important;
}
.menu-tab-item:hover {
  background-color: #fff;
  color: #000 !important;
}
.background-img {
  background-image: url("images/sl_220602_shopee-lazada-tiki.jpg");
  height: 400px;
  position: relative;
  display: flex;
  justify-content: center;
}

.filter-box {
  border: 5px solid #ffc709;
  border-radius: 5px;
  padding: 0.5em;
  margin-right: 2em;
}
</style>
