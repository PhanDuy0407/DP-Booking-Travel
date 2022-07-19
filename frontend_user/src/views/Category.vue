<template>
  <div>
    <v-row>
      <v-col>
        <div>
          <!-- <siderbar /> -->
          <v-card>
            <v-card-title class="justify-center">Bộ lọc tìm kiếm</v-card-title>
            <v-card-text>
              <h6>Điểm đi</h6>
              <v-autocomplete
                v-model="objSearch.startPlaceId"
                dense
                :items="places"
                outlined
                item-value="id"
                item-text="name"
              >
              </v-autocomplete>

              <h6>Điểm đến</h6>
              <v-autocomplete
                v-model="objSearch.endPlaceId"
                dense
                :items="places"
                outlined
                item-value="id"
                item-text="name"
              >
              </v-autocomplete>

              <h6>Ngày đi</h6>
              <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="objSearch.startTime"
                transition="scale-transition"
                offset-y
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="objSearch.startTime"
                    v-bind="attrs"
                    v-on="on"
                    outlined
                    dense
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="objSearch.startTime"
                  no-title
                  scrollable
                  @change="$refs.menu.save(objSearch.startTime)"
                >
                </v-date-picker>
              </v-menu>

              <h6>Số ngày</h6>
              <v-select dense outlined v-model="objSearch.period" :items="periods">
              </v-select>

              <h6>Số người</h6>
              <v-select dense outlined v-model="numOfPeople"> </v-select>

              <h6>Giá</h6>
              <v-range-slider
                v-model="price"
                min="0"
                max="20000000"
                step="4000000"
              ></v-range-slider>
              <div>
                {{formatCurrency(price[0])}} - {{formatCurrency(price[1])}}
              </div>
            </v-card-text>
            <v-card-actions class="justify-center">
              <v-btn color="primary" @click="clickSearch">Tìm kiếm</v-btn>
            </v-card-actions>
          </v-card>
        </div>
      </v-col>
      <v-col cols="12" lg="12" xl="8">
        <div>
          <div>
            <div>
              <h2 class="text-h4 font-weight-bold text-center">
                Danh sách Tour
              </h2>

              <!-- <h4 class="text-h6">Some category description goes here</h4> -->
            </div>

            <v-divider class="my-4"></v-divider>

            <v-row>
              <v-col
                cols="12"
                md="6"
                lg="4"
                v-for="(item, i) in tours"
                :key="i"
              >
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
                      :to="{ name: 'Detail', params: { id: item.id } }"
                    >
                      <v-img
                        :src="item.mainImageUrl"
                        :aspect-ratio="16 / 9"
                        gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                        height="200px"
                        class="elevation-2"
                        style="border-radius: 16px"
                      >
                      </v-img>
                      <!-- <v-card-title>Bangkok - Pattaya (Khách sạn 4*, tặng Show Alcazar và
                          Buffet tại BaiYoke Sky)</v-card-title> -->
                      <v-card-text>
                        <div v-snip="3" class="text-body-1 font-weight-bold black--text" style="min-height: 4.5em" v-b-tooltip.hover :title="item.name">
                          {{ item.name }}
                        </div>

                        <div class="text-body-1 pt-5 black--text">
                          Nơi khởi hành: {{ item.startPlaceName }}
                        </div>
                        <div class="text-body-1 black--text">
                          Giá:
                          <span class="text-body-1 red--text font-weight-bold"
                            >{{ formatCurrency(item.price) }} </span
                          >
                        </div>
                        <!-- <div class="text-body-1 red--text font-weight-bold">
                          8.000.000đ
                        </div> -->
                        <div class="text-body-1 text-center py-5 indigo--text">
                          Còn: 00 ngày 09:09:00
                        </div>
                        <div class="text-body-1 text-right black--text">
                          <span class="text-decoration-underline"
                            >Số chỗ còn:</span
                          >
                          <span class="red--text">{{item.placeOrderMax}}</span>
                        </div>
                      </v-card-text>

                      <v-card-actions class="d-flex justify-space-between">
                        <v-btn color="error" :to="{ name: 'Order', params: { id: item.id } }">
                          <v-icon>mdi-cart</v-icon>
                          Đặt ngay
                        </v-btn>
                        <v-btn color="indigo" outlined :to="{ name: 'Detail', params: { id: item.id } }"> Xem chi tiết </v-btn>
                      </v-card-actions>
                    </v-card>
                  </div>
                </v-hover>
              </v-col>
            </v-row>
          </div>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  name: "Category",
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
  data() {
    return {
      menu: false,
      periods: [
        { value: 1, text: "1-3 ngày" },
        { value: 2, text: "4-7 ngày" },
        { value: 3, text: "8-14 ngày" },
        { value: 4, text: "Trên 14 ngày" },
      ],
      numOfDay: null,
      numOfPeople: null,
      price: [0, 20000000],
    };
  },
  computed: {
    ...mapGetters({
      objSearch: "tourList/getObjSearch",
      tours: "tourList/getTours",
      places: "place/getPlaces",
    }),
  },
  created() {
    this.getTours(this.objSearch);
  },
  methods: {
    ...mapActions({
      getTours: "tourList/getTours",
      getPlaces: "place/getAll",
      setObjSearch: "tourList/setObjSearch",
    }),
    async clickSearch() {
      console.log(this.objSearch);
      
      var fromPeriod;
      var toPeriod;
      switch (this.objSearch.period) {
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
        ...this.objSearch,
        fromPeriod: fromPeriod,
        toPeriod: toPeriod,
        fromPrice: this.price[0],
        toPrice: this.price[1],
      };
      console.log(obj);
      this.setObjSearch(obj);
      await this.getTours(this.objSearch)
    },

    formatCurrency(currency){
      return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(currency)
    }
  },
};
</script>
