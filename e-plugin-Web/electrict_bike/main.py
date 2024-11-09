from flask import Flask
from public import public
from admin import admin
from api import api
from recharge_bunk import bunk
app=Flask(__name__)
app.secret_key="zzz"
app.register_blueprint(public)
app.register_blueprint(admin,url_prefix='/admin')
app.register_blueprint(bunk,url_prefix="/bunk")
app.register_blueprint(api,url_prefix='/api')
app.run(debug=True,port=3306,host="0.0.0.0")