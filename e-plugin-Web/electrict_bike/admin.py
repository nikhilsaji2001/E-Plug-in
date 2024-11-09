from flask import *
from database import *
import uuid

admin=Blueprint('admin',__name__)

@admin.route('/admin_home')
def admin_home():
	return render_template('admin_home.html')

@admin.route('/admin_add_service_center',methods=['get','post'])
def admin_add_service_center():
	data={}
	qry="select * from service_center"
	data['sc']=select(qry)
	if 'submit' in request.form:
		firstname=request.form['firstname']
		# lastname=request.form['lastname']
		place=request.form['place']
		phone=request.form['phone']
		email=request.form['email']
		latitude=request.form['latitude']
		longitude=request.form['longitude']
		z="insert into service_center values(null,'null','%s','%s','%s','%s','%s','%s','%s')"%(firstname,lastname,place,phone,email,latitude,longitude)
		insert(z)
		flash("Added successfully");
	if 'action' in request.args:
		action=request.args['action']
		cid=request.args['cid']
	else:
		action=None
	if action=="delete":	
		q="DELETE FROM `service_center` WHERE `center_id`='%s'"%(cid)
		delete(q)
		flash("deleted")
		return redirect(url_for('admin.admin_add_service_center'))
	if action=="update":
		q="select * from `service_center` WHERE `center_id`='%s'"%(cid)
		data['mech']=select(q)	
	if "sub_upd" in request.form:
		
		firstname=request.form['firstname']
		# lastname=request.form['lastname']
		place=request.form['place']
		phone=request.form['phone']
		email=request.form['email']
		latitude=request.form['latitude']
		longitude=request.form['longitude']
		q="update service_center set firstname='%s',lastname='%s',place='%s',email='%s',phone='%s',latitude='%s',longitude='%s' where center_id='%s'"%(firstname,lastname,place,email,phone,latitude,longitude,cid)
		update(q)
		flash("Updated successfully")
		return redirect(url_for('admin.admin_add_service_center'))
	return render_template('admin_add_service_center.html',data=data)

@admin.route('/admin_view_mechanic',methods=['get','post'])
def admin_view_mechanic():
	data={}
	q="select * from mechanic"
	res=select(q)
	data['mechanic']=res
	if 'action' in request.args:
		action=request.args['action']
		mid=request.args['mid']
	else:
		action=None
	if action=="accept":
		q="update mechanic set status='accepted' where mechanic_id='%s'"%(mid)
		update(q)
		q="update login set usertype='mechanic' where login_id=(select login_id from mechanic where mechanic_id='%s')"%(mid)
		update(q)
		flash("accepted")
		return redirect(url_for('admin.admin_view_mechanic'))
	if action=="reject":
		q="update mechanic set status='rejected' where mechanic_id='%s'"%(mid)
		update(q)
		q="update login set usertype='pending' where login_id=(select login_id from mechanic where mechanic_id='%s')"%(mid)
		update(q)
		flash("rejected")
		return redirect(url_for('admin.admin_view_mechanic'))			
	return render_template('admin_view_mechanic.html',data=data)

@admin.route('/admin_view_sparepart',methods=['get','post'])
def admin_view_sparepart():
	data={}
	q="select * from sparepart inner join login using(login_id)"
	res=select(q)
	data['spare']=res
	if 'action' in request.args:
		action=request.args['action']
		lid=request.args['lid']
	else:
		action=None
	if action=='accept':
		q="update login set usertype='spare' where login_id='%s'"%(lid)
		update(q)
		print(q)
	if action=='reject':
		q="update login set usertype='reject' where login_id='%s'"%(lid)
		update(q)	
	return render_template('admin_view_spareparts.html',data=data)

@admin.route('/admin_view_user',methods=['get','post'])
def admin_view_user():
	data={}
	q="select * from user"
	res=select(q)
	data['user']=res
	return render_template('admin_view_user.html',data=data)

@admin.route('/admin_view_complaint',methods=['get','post'])
def admin_view_complaint():
	data={}
	q="select * from complaint"
	res=select(q)
	data['comp']=res
	return render_template('admin_view_complaint.html',data=data)

@admin.route('/admin_send_reply',methods=['get','post'])
def admin_send_reply():
	complaint_id=request.args['complaint_id']
	if 'submit' in request.form:
		reply=request.form['reply']
		q="update complaint set reply='%s' where complaint_id='%s' "%(reply,complaint_id)
		update(q)
		return redirect(url_for('admin.admin_view_complaint'))

	return render_template('admin_send_reply.html')

@admin.route('/admin_view_payment',methods=['get','post'])
def admin_view_payment():
	data={}
	q="select * from payment"
	res=select(q)
	data['pay']=res
	return render_template('admin_view_payment.html',data=data)					

@admin.route('/admin_view_rating',methods=['get','post'])
def admin_view_rating():
	data={}
	q="SELECT * FROM rating INNER JOIN `user` USING(`user_id`) INNER JOIN `mechanicrequest` ON `mrequest_id`=`requested_id`"
	res=select(q)
	data['rate']=res
	return render_template('admin_view_rating.html',data=data)		

@admin.route('/admin_add_type',methods=['get','post'])
def admin_add_type():
	data={}
	qry="select * from station_type"
	data['comp']=select(qry)
	if 'submit' in request.form:
		type=request.form['reply']
		voltage=request.form['voltage']
		q="INSERT INTO `station_type` VALUES(NULL,'%s','%s')"%(type,voltage)
		insert(q)
		return redirect(url_for('admin.admin_add_type'))
	return render_template('admin_add_type.html',data=data)	

@admin.route('/admin_update_type',methods=['get','post'])
def admin_update_type():
	data={}
	session['type_id']=type_id=request.args['type_id']
	qry="select * from station_type where type_id='%s'"%(type_id)
	data['up']=select(qry)
	if 'update' in request.form:
		type=request.form['reply']
		voltage=request.form['voltage']
		q="update  `station_type` set voltage='%s',type_name='%s' where type_id='%s'"%(voltage,type,session['type_id'])
		a=update(q)
		if a:
			flash("Updated Successfully")
			return redirect(url_for('admin.admin_add_type'))
	return render_template('admin_update_type.html',data=data)	



@admin.route('/admin_delete_type',methods=['get','post'])
def admin_delete_type():
	data={}
	type_id=request.args['type_id']
	q="delete from `station_type`  where type_id='%s'"%(type_id)
	a=update(q)
	if a:
		flash("Deleted Successfully")
		return redirect(url_for('admin.admin_add_type'))
	return render_template('admin_add_type.html',data=data)	
