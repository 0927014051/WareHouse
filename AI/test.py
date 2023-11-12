import json
import  pandas as pd
from sklearn.linear_model import LinearRegression
# Chuỗi JSON đầu vào
json_data = '[{"month": 5, "special_event": 1, "season": 2, "product_id": "10001"}, {"month": 6, "special_event": 1, "season": 2, "product_id": "10003"}, {"month": 7, "special_event": 1, "season": 2, "product_id": "10001"}, {"month": 8, "special_event": 1, "season": 2, "product_id": "10001"}, {"month": 9, "special_event": 1, "season": 2, "product_id": "10001"}]'

# Chuyển chuỗi JSON thành một mảng chứa các dictionaries
data_list = json.loads(json_data)

# In mảng chứa dictionaries
for item in data_list:
    print(item)
    if(item["product_id"]=='10001'):
        y = ""
        y = y + item["product_id"]
        y = y +".csv"
    data = pd.read_csv(y)

    # Chọn tất cả biến độc lập và biến phụ thuộc
    x = data[['special_event', 'season', 'month']]
    y = data['quantity']

    model = LinearRegression()
    model.fit(x, y)
    print(model.coef_)
    print(model.intercept_)