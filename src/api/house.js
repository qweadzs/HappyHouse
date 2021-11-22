import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  house.get(``, { params: params }).then(success).catch(fail);
}
// 위시리스트 등록하기
function wishregist(wishlist, success, fail) {
  api.post(`/map`, JSON.stringify(wishlist)).then(success).catch(fail);
}

export { sidoList, gugunList, houseList, wishregist };
