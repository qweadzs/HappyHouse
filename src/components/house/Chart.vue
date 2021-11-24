<script>
import { mapState } from "vuex";
import { Pie } from "vue-chartjs";
const houseStore = "houseStore";
export default {
  extends: Pie,
  name: "Chart",
  data() {},
  watch: {
    house: function () {
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
      this.renderChart(
        {
          labels: ["Books", "Magazines", "Newspapers"],
          datasets: [
            {
              backgroundColor: [this.gradient, this.gradient2, "#00D8FF"],
              data: [40, 20, 10],
            },
          ],
        },
        { responsive: true, maintainAspectRatio: false }
      );
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
