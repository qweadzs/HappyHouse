<script>
import { mapState } from "vuex";
import { Pie } from "vue-chartjs";
const houseStore = "houseStore";
export default {
  extends: Pie,
  name: "Chart",
  data() {},
  props: {
    datas: Object,
  },
  watch: {
    datas: function () {
      console.log(this.datas);
      this.gradient = this.$refs.canvas
        .getContext("2d")
        .createLinearGradient(0, 0, 0, 450);
      this.gradient2 = this.$refs.canvas
        .getContext("2d")
        .createLinearGradient(0, 0, 0, 450);

      this.gradient.addColorStop(0, "rgba(255, 0,0, 0.5)");
      this.gradient.addColorStop(0.5, "rgba(255, 0, 0, 0.25)");
      this.gradient.addColorStop(1, "rgba(255, 0, 0, 0)");

      this.gradient2.addColorStop(0, "rgba(0, 231, 255, 0.9)");
      this.gradient2.addColorStop(0.5, "rgba(0, 231, 255, 0.25)");
      this.gradient2.addColorStop(1, "rgba(0, 231, 255, 0)");
      var array = [];
      for (var key in this.datas) {
        array.push(this.datas[key]);
      }
      this.renderChart(
        {
          labels: ["은행", "카페", "편의점", "마트", "주유소", "약국"],
          datasets: [
            {
              backgroundColor: [
                this.gradient,
                this.gradient2,
                "#00D8FF",
                "#FFD700",
                "#FF1493",
                "#FFF8DC",
              ],
              // 여기에 데이터 삽입
              data: array,
            },
          ],
        },
        { responsive: true, maintainAspectRatio: false }
      );
      console.log("차트 실행");
    },
  },
  computed: {
    ...mapState(houseStore, ["house"]),
    // ...mapGetters(houseStore, ["house"]),
    // ...mapGetters(houseStore, ["house"]),
  },
};
</script>

<style></style>
