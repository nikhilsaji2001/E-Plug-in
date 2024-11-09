from flask import *
from database import *

public=Blueprint('public',__name__)

@public.route('/')
def home():
	return render_template('index.html')

@public.route('/login',methods=['get','post'])
def login():
	if 'submit' in request.form:
		username=request.form['username']
		password=request.form['password']
		q="select * from login where username='%s' and password='%s'"%(username,password)
		res=select(q)
		if res:
			session['lid']=res[0]['login_id']
			if res[0]['usertype']=="admin":
				flash("Login successful")
				return redirect(url_for('admin.admin_home'))
			elif res[0]['usertype']=="bunk":
				qry="select * from bunk where login_id='%s'"%(session['lid'])
				res=select(qry)
				session['bunk_id']=res[0]['bunk_id']
				flash("Login successful")
				return redirect(url_for('bunk.bunk_home'))
			elif res[0]['usertype']=="service_center":
				qry="select * from service_center where login_id='%s'"%(session['lid'])
				res=select(qry)
				session['center_id']=res[0]['center_id']
				flash("Login successful")
				return redirect(url_for('center.center_home'))
	return render_template('login.html')	


@public.route('/recharge_bunk_registeration',methods=['get','post'])
def recharge_bunk_registeration():
	if 'submit' in request.form:
		firstname=request.form['firstname']
		place=request.form['place']
		latitude=request.form['latitude']
		phone=request.form['phone']
		email=request.form['email']
		longitude=request.form['longitude']
		username=request.form['username']
		password=request.form['password']
		qry="insert into login values(null,'%s','%s','bunk')"%(username,password)
		lid=insert(qry)
		q="INSERT INTO `bunk` VALUES(NULL,'%s','%s','%s','%s','%s','%s','%s')"%(lid,firstname,place,latitude,longitude,email,phone)
		q=insert(q)
		if q:
			flash("Registered successfully")
			return redirect(url_for('public.login'))			
	return render_template('bunk_register.html')


# @public.route('/service_center_registeration',methods=['get','post'])
# def service_center_registeration():
# 	if 'submit' in request.form:
# 		firstname=request.form['firstname']
# 		lastname=request.form['lastname']
# 		place=request.form['place']
# 		phone=request.form['phone']
# 		email=request.form['email']
# 		latitude=request.form['latitude']
# 		longitude=request.form['longitude']
# 		username=request.form['username']
# 		password=request.form['password']
# 		qry="insert into login values(null,'%s','%s','service_center')"%(username,password)
# 		lid=insert(qry)
# 		q="INSERT INTO `service_center` VALUES(NULL,'%s','%s','%s','%s','%s','%s','%s','%s')"%(lid,firstname,lastname,place,phone,email,latitude,longitude)
# 		q=insert(q)
# 		if q:
# 			flash("Registered successfully")
# 			return redirect(url_for('public.login'))			
# 	return render_template('center_registeration.html')