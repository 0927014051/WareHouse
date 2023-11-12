import pandas as pd
from sklearn.linear_model import LinearRegression
from flask import Flask, request, jsonify
import json

app = Flask(__name__)

# Huấn luyện mô hình hồi quy tuyến tính đa biến
def training(product_id):
    file=f"{product_id}.csv"
    data = pd.read_csv(file, delimiter=';')

    # Chọn tất cả biến độc lập và biến phụ thuộc
    x = data[['special_event', 'season', 'month']]
    y = data['quantity']

    model = LinearRegression()
    model.fit(x, y)
    return model
#print(model.coef_)
#print(model.intercept_)


# Hàm dự đoán số lượng bán cho dữ liệu mới
def predict_new_data(model, new_x):

    predicted_y = model.predict([new_x])
    return predicted_y[0]

    predicted_value = predict_new_data(model, new_x)
    return  predicted_value


#hàm chuyển product_id thành float


@app.route('/predict', methods=['POST'])
def predict_and_update():

    data = request.get_json()  # Lấy dữ liệu JSON từ yêu cầ

    # Tạo một mảng để chứa các từ điển dự đoán

    response_data_list = []
    print(data)

    for item in data:
        month =  int(item["month"])

        special_event = int(item["special_event"])
        season = int(item["season"])
        product_id = item["product_id"]
        print(type(month),type(special_event), type(season), type(product_id))
        # Dự đoán giá trị predicted_value
        model1 = training(product_id)
        new_x1 = [special_event, season, month]

        predicted_value = predict_new_data(model1, new_x1)

        # Tạo một từ điển chứa product_id và predicted_value
        response_data = {
            'product_id': product_id,
            'predicted_value': predicted_value
        }

        # Thêm từ điển này vào mảng response_data_list
        response_data_list.append(response_data)

    # Trả về mảng response_data_list dưới dạng JSON
    return jsonify(response_data_list)





if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5000)