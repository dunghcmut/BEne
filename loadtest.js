import http from 'k6/http';
import { sleep, check } from 'k6';

// Cấu hình thử nghiệm
export const options = {
  stages: [
    { duration: '10s', target: 100 }, // Tăng lên 10 người dùng trong 10 giây
    { duration: '20s', target: 200 }, // Duy trì 50 người dùng trong 20 giây
    { duration: '10s', target: 0 },  // Giảm về 0 trong 10 giây
  ],
  thresholds: {
    http_req_duration: ['p(95)<1000'], // 95% yêu cầu dưới 500ms
    http_req_failed: ['rate<0.01'],   // Tỷ lệ lỗi dưới 1%
  },
};

// Định nghĩa URL và dữ liệu
const BASE_URL = 'http://localhost:8080'; // Thay đổi nếu cần

// Dữ liệu cho POST /api/admin/menu
const menuPayload = JSON.stringify({
  id: 0,
  name: 'Test Item',
  description: 'A test menu item',
  category: 'Food',
  price: 100,
  status: 'available',
  imageUrl: 'http://example.com/image.jpg',
  recommend: true,
});
const headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
};

export default function () {
  let menuRes = http.post(`${BASE_URL}/api/admin/menu`, menuPayload, { headers });
  check(menuRes, {
    'POST /api/admin/menu status is 200': (r) => r.status === 200,
  });
  let todayMenuRes = http.get(`${BASE_URL}/api/menu/today`, { headers });
  check(todayMenuRes, {
    'GET /api/menu/today status is 200': (r) => r.status === 200,
  });
  sleep(0.25);
}