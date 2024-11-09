from flask import *
from database import *
import uuid
api=Blueprint('api',__name__)

@api.route('/login')
def login():
	data={}
	username=request.args['username']
	password=request.args['password']
	q="select * from login where username='%s' and password='%s'"%(username,password)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	return str(data)

@api.route('/mechanic_view_request',methods=['get','post'])
def mechanic_view_request():
	data={}
	lid=request.args['lid']
	q="select * from mechanicrequest inner join user using(user_id) where mechanic_id=(select mechanic_id from mechanic where login_id='%s')"%(lid)
	res=select(q)
	print(res)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
		
	return str(data)

@api.route('/mechanic_view_customer')
def mechanic_view_customer():
	data={}
	uid=request.args['uid']
	q="select * from user where user_id='%s'"%(uid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
		
	return str(data)		

@api.route('/mechanic_accept_request',methods=['get','post'])
def mechanic_accept_request():
	data={}
	lid=request.args['lid']
	rid=request.args['rid']
	q="update mechanicrequest set status='accept' where mrequest_id='%s'"%(rid)
	update(q)
	data['status']="success"	
	return str(data)	

@api.route('/mechanic_reject_request',methods=['get','post'])
def mechanic_reject_request():
	data={}
	lid=request.args['lid']
	rid=request.args['rid']
	q="update mechanicrequest set status='reject' where mrequest_id='%s'"%(rid)
	update(q)
	data['status']="success"	
	return str(data)			

@api.route('/mechanic_upload_servicecharge')
def mechanic_upload_servicecharge():
	data={}
	lid=request.args['lid']
	uid=request.args['uid']
	amount=request.args['amount']
	q="update mechanicrequest set serviceamount='%s' where user_id='%s' and mechanic_id=(select mechanic_id from mechanic where login_id='%s')"%(amount,uid,lid)
	update(q)
	data['status']="success"	
	return str(data)	

@api.route('/mechanic_send_complaint')
def mechanic_send_complaint():
	data={}
	lid=request.args['lid']
	complaint=request.args['complaint']
	q="insert into complaint values(null,(select mechanic_id from mechanic where login_id='%s'),'%s','pending',curdate(),0)"%(lid,complaint)
	insert(q)
	data['status']="success"
	data['method']="mechanic_send_complaint"
	return str(data)

@api.route('/mechanic_view_profile')
def mechanic_view_profile():
	data={}
	lid=request.args['lid']
	q="select * from mechanic where login_id='%s'"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="mechanic_view_profile"	
	return str(data)

@api.route('/mechanic_update_profile')
def mechanic_update_profile():
	data={}
	lid=request.args['lid']
	firstname=request.args['firstname']
	lastname=request.args['lastname']
	place=request.args['place']
	phone=request.args['phone']
	email=request.args['email']
	latitude=request.args['latitude']
	longitude=request.args['longitude']
	q="update mechanic set firstname='%s',lastname='%s',place='%s',phone='%s',email='%s',latitude='%s',longitude='%s' where mechanic_id=(select mechanic_id from mechanic where login_id='%s')"%(firstname,lastname,place,phone,email,latitude,longitude,lid)
	update(q)
	data['status']="success"
	data['method']="mechanic_update_profile"
	return str(data)

@api.route('/mechanic_view_payment',methods=['get','post'])
def mechanic_view_payment():
	data={}
	rid=request.args['rid']
	q="select * from payment where requested_id='%s' and requestedfor='mechanicrequest'"%(rid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
		
	return str(data)		


@api.route('/mechanic_registration')
def mechanic_registration():
	data={}
	firstname=request.args['firstname']
	lastname=request.args['lastname']
	place=request.args['place']	
	phone=request.args['phone']
	email=request.args['email']
	username=request.args['username']
	password=request.args['password']
	latitude=request.args['latitude']
	longitude=request.args['longitude']
	q="select * from login where username='%s' and password='%s'"%(username,password)
	res=select(q)
	if res:
		data['status']="duplicate"
	else:
		q="insert into login values(null,'%s','%s','pending')"%(username,password)
		id=insert(q)
		z="insert into mechanic values(null,'%s','%s','%s','%s','%s','%s','%s','%s')"%(id,firstname,lastname,place,phone,email,latitude,longitude)
		insert(z)
		data['status']="success"
	data['method']="mechanic_registration"	
	return str(data)

@api.route('bunk_view_request',methods=['get','post'])
def bunk_view_request():
	data={}
	lid=request.args['lid']
	q="select * from rechargerequest inner join user using(user_id) where bunk_id=(select bunk_id from bunk where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
		
	return str(data)

@api.route('bunk_send_service_charge')
def bunk_send_service_charge():
	data={}
	rid=request.args['rid']
	amount=request.args['amount']
	q="update rechargerequest set amount='%s' where rrequest_id='%s'"%(amount,rid)
	update(q)
	data['status']="success"
	data['method']="bunk_send_service_charge"
	return str(data)		

@api.route('/bunk_view_profile')
def bunk_view_profile():
	data={}
	lid=request.args['lid']
	q="select * from bunk where bunk_id=(select bunk_id from bunk where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="bunk_view_profile"	
	return str(data)

@api.route('/bunk_update_profile')
def bunk_update_profile():
	data={}
	lid=request.args['lid']
	name=request.args['name']

	place=request.args['place']
	
	latitude=request.args['latitude']
	longitude=request.args['longitude']
	q="update bunk set name='%s',place='%s',latitude='%s',longitude='%s' where bunk_id=(select bunk_id from bunk where login_id='%s')"%(name,place,latitude,longitude,lid)
	update(q)
	data['status']="success"
	data['method']="bunk_update_profile"
	return str(data)	

@api.route('bunk_view_payment',methods=['get','post'])
def bunk_view_payment():
	data={}
	rid=request.args['rid']
	q="select * from payment where requested_id='%s'"%(rid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
		
	return str(data)

@api.route('/bunk_view_complaints',methods=['get','post'])
def bunk_view_complaints():
	data={}
	
	q="select * from complaint inner join user using(user_id)"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
		
	return str(data)			



@api.route('/user_registration')
def user_registration():
	data={}
	firstname=request.args['firstname']
	lastname=request.args['lastname']
	place=request.args['place']	
	phone=request.args['phone']
	email=request.args['email']
	username=request.args['username']
	password=request.args['password']
	latitude=request.args['latitude']
	longitude=request.args['longitude']
	q="select * from login where username='%s' and password='%s'"%(username,password)
	res=select(q)
	if res:
		data['status']="duplicate"
	else:
		q="insert into login values(null,'%s','%s','user')"%(username,password)
		id=insert(q)
		z="insert into user values(null,'%s','%s','%s','%s','%s','%s','%s','%s')"%(id,firstname,lastname,place,phone,email,latitude,longitude)
		insert(z)
		data['status']="success"
	data['method']="user_registration"	
	return str(data)	

@api.route('/user_view_profile')
def user_view_profile():
	data={}
	lid=request.args['lid']
	q="select * from user where user_id=(select user_id from user where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="user_view_profile"	
	return str(data)

@api.route('/user_update_profile')
def user_update_profile():
	data={}
	lid=request.args['lid']
	firstname=request.args['firstname']
	lastname=request.args['lastname']
	place=request.args['place']
	phone=request.args['phone']
	email=request.args['email']
	latitude=request.args['latitude']
	longitude=request.args['longitude']
	q="update user set firstname='%s',lastname='%s',place='%s',phone='%s',email='%s',latitude='%s',longitude='%s' where user_id=(select user_id from user where login_id='%s')"%(firstname,lastname,place,phone,email,latitude,longitude,lid)
	insert(q)
	data['status']="success"
	data['method']="user_update_profile"
	return str(data)

@api.route('/user_rate_bunk')
def user_rate_bunk():
	data={}

	rating=request.args['rating']
	bunkid=request.args['bunkid']
	lid=request.args['lid']

	q="SELECT * FROM `rating` WHERE `user_id`=(SELECT `user_id` FROM `user` WHERE `login_id`='%s') and bunk_id='%s'"%(lid,bunkid)
	res=select(q)
	if res:
		rid=res[0]['rating_id']
		z="update rating set rating='%s',date='curdate()' where rating_id='%s'"%(rating,rid)
		update(z)
		data['status'] = 'success'
	else:
		q="insert into rating values(null,(select user_id from user where login_id='%s'),'%s','%s',curdate())"%(lid,bunkid,rating)
		id=insert(q)
		if id:
			data['status'] = 'success'
			
		else:
			data['status'] = 'failed'
	data['method'] = 'user_rate_bunk'
	
	return str(data)	

@api.route('/user_view_review_bunk',methods=['get','post'])
def user_view_review_bunk():
	data = {}

	lid=request.args['lid']
	bunkid=request.args['bunkid']
	
	
	q="SELECT * FROM `rating` WHERE `user_id`=(SELECT `user_id` FROM `user` WHERE `login_id`='%s') and bunk_id='%s' "%(lid,bunkid)
	print(q)
	result=select(q)
	if result:
		data['status'] = 'success'
		data['data'] = result[0]['rating']
		
		
	else:
		data['status'] = 'failed'
	data['method'] = 'user_view_review_bunk'
	return str(data)


@api.route('/user_view_mechanic',methods=['get','post'])
def user_view_mechanic():
	data={}
	lati=request.args['lati']
	longi=request.args['longi']
	q="SELECT *,(3959 * ACOS ( COS ( RADIANS('%s') ) * COS( RADIANS( latitude) ) * COS( RADIANS( longitude ) - RADIANS('%s') ) + SIN ( RADIANS('%s') ) * SIN( RADIANS(latitude ) ))) AS user_distance from mechanic  ORDER BY user_distance ASC" % (lati,longi,lati)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="user_view_mechanic"		
	return str(data)

@api.route('/user_view_mechanics',methods=['get','post'])
def user_view_mechanics():	
	data={}
	p="%"+request.args['p']+"%"
	q="select * from mechanic where firstname like '%s'"%(p)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="user_view_mechanic"		
	return str(data)

# @api.route('/user_request_mechanic',methods=['get','post'])
# def user_request_mechanic():
# 	data={}
# 	lid=request.args['lid']
# 	mid=request.args['mid']
# 	vid=request.args['vid']
# 	q="insert into mechanicrequest values(null,(select user_id from user where login_id='%s'),'%s','how much',curdate(),'pending','%s')"%(lid,mid,vid)
# 	insert(q)
# 	data['status']="success"
# 	data['method']="user_request_mechanic"	
# 	return str(data)

@api.route('/user_view_recharge_request',methods=['get','post'])
def user_view_recharge_request():	
	data={}
	lid=request.args['lid']
	q="SELECT * FROM `rechargerequest` INNER JOIN `my_type` USING (`my_type_id`) INNER JOIN `bunk` USING (`bunk_id`) WHERE user_id=(SELECT user_id FROM USER WHERE login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"	
	return str(data)

@api.route('/user_view_bunk',methods=['get','post'])
def user_view_bunk():
	data={}
	lati=request.args['lati']
	longi=request.args['longi']
	# q=""" SELECT *,(3959 * ACOS ( COS ( RADIANS('%s') ) * COS( RADIANS( bunk.`latitude`) ) * COS( RADIANS( bunk.`latitude` ) 
 # - RADIANS('%s') ) + SIN ( RADIANS('%s') ) * SIN( RADIANS(`latitude` ) ))) AS user_distance FROM `bunk`
 #  HAVING user_distance<31.068
 #  ORDER BY user_distance ASC""" % (lati,longi,lati)
	q="SELECT *,(3959 * ACOS ( COS ( RADIANS('%s') ) * COS( RADIANS( latitude) ) * COS( RADIANS( longitude ) - RADIANS('%s') ) + SIN ( RADIANS('%s') ) * SIN( RADIANS(latitude ) ))) AS user_distance from bunk   ORDER BY user_distance ASC" % (lati,longi,lati)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="user_view_bunk"		
	return str(data)

@api.route('/user_view_bunks',methods=['get','post'])
def user_view_bunks():	
	data={}
	p="%"+request.args['p']+"%"
	q="select * from bunk where name like '%s'"%(p)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="user_view_bunk"		
	return str(data)

@api.route('/user_request_bunk',methods=['get','post'])
def user_request_bunk():
	data={}
	types=request.args['type']
	vehi=request.args['vehi']
	lid=request.args['lid']
	date=request.args['date']
	q="insert into rechargerequest values(null,(select user_id from user where login_id='%s'),'%s',null,'%s','%s','pending')"%(lid,types,date,vehi)
	insert(q)
	data['status']="success"
	data['method']="user_request_bunk"	
	return str(data)	

@api.route('/user_view_mechanic_request',methods=['get','post'])
def user_view_mechanic_request():	
	data={}
	lid=request.args['lid']
	q="select * from mechanicrequest inner join mechanic using(mechanic_id) where user_id=(select user_id from user where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"	
	data['method']="user_view_mechanic_request"
	return str(data)

@api.route('/user_cancel_mrequest',methods=['get','post'])
def user_cancel_mrequest():
	data={}
	
	rid=request.args['rid']
	q="update mechanicrequest set status='cancelled' where mrequest_id='%s'"%(rid)
	update(q)
	data['status']="success"
	data['method']="user_cancel_mrequest"
	return str(data)

@api.route('/user_rate_mechanic')
def user_rate_mechanic():
	data={}

	rating=request.args['rating']
	rid=request.args['rid']
	lid=request.args['lid']

	q="SELECT * FROM `rating` WHERE `user_id`=(SELECT `user_id` FROM `user` WHERE `login_id`='%s')"%(lid)
	res=select(q)
	if res:

		z="update rating set rating='%s',date=curdate() where requested_id='%s' and user_id=(select user_id from user where login_id='%s')"%(rating,rid,lid)
		update(z)
		data['method'] = 'user_rate_sparepart'
		data['status'] = 'success'
	else:
		q="insert into rating values(null,(select user_id from user where login_id='%s'),'%s','%s',curdate())"%(lid,rid,rating)
		id=insert(q)
		if id>0:
			data['status'] = 'success'
		else:
			data['status'] = 'failed'
		data['method'] = 'user_rate_mechanic'
	return str(data)

@api.route('/user_view_review_mechanic',methods=['get','post'])
def user_view_review_mechanic():
	data = {}
	lid=request.args['lid']
	q="SELECT * FROM `rating` WHERE `user_id`=(SELECT `user_id` FROM `user` WHERE `login_id`='%s') "%(lid)
	print(q)
	result=select(q)
	if result:
		data['status'] = 'success'
		data['data'] = result[0]['rating']
		
		
	else:
		data['status'] = 'failed'
	data['method'] = 'user_view_review_mechanic'
	return str(data)	

# @api.route('/user_view_spareparts',methods=['get','post'])
# def user_view_spareparts():
# 	data={}
# 	q="select * from product inner join sparepart using(sparepart_id)"
# 	res=select(q)
# 	if res:
# 		data['status']="success"
# 		data['data']=res
# 	else:
# 		data['status']="failed"	
# 	data['method']="user_view_spareparts"	
# 	return str(data)	

# @api.route('/user_view_products',methods=['get','post'])
# def user_view_products():	
# 	data={}
# 	p="%"+request.args['p']+"%"
# 	q="select * from product inner join sparepart using(sparepart_id) where product_name like '%s'"%(p)
# 	res=select(q)
# 	if res:
# 		data['status']="success"
# 		data['data']=res
# 	else:
# 		data['status']="failed"	
# 	data['method']="user_view_spareparts"		
# 	return str(data)	

# @api.route('/user_order_product')
# def user_order_product():
# 	data={}
# 	lid=request.args['lid']	
# 	pid=request.args['pid']
# 	spid=request.args['spid']
# 	amount=request.args['amount']
# 	q="SELECT * FROM `order` WHERE user_id=(SELECT user_id FROM USER WHERE login_id='%s')"%(lid)
# 	res=select(q)
# 	print(res)
# 	if res:
# 		oid=res[0]['order_id']
# 		q="update `order` set amount=amount+'%s',status='pending' where order_id='%s'"%(amount,oid)
# 		update(q)
# 		x="select * from `orderdetail` where order_id='%s' and product_id='%s'"%(oid,spid)
# 		res1=select(x)
# 		if res1:
# 			y="update `orderdetail` set amount=amount+'%s' where order_id='%s' and product_id='%s'"%(amount,oid,spid)
# 			update(y)
# 		else:	
# 			z="insert into orderdetail values(null,'%s','%s','%s',curdate())"%(oid,pid,amount)
# 			insert(z)
# 	else:
# 		q="insert into `order` values(null,'%s',(select user_id from user where login_id='%s'),'%s',curdate(),'pending')"%(spid,lid,amount)
# 		id=insert(q)
# 		z="insert into `orderdetail` values(null,'%s','%s','%s',curdate())"%(id,pid,amount)
# 		insert(z)	
# 	data['status']="success"
# 	data['method']="user_order_product"	
# 	return str(data)


# @api.route('/user_view_product',methods=['get','post'])
# def user_view_product():
# 	data={}
# 	q="select * from product"
# 	res=select(q)
# 	if res:
# 		data['status']="success"
# 		data['data']=res
# 	else:
# 		data['status']="failed"	
# 	return str(data)



@api.route('/user_pay_amount',methods=['get','post'])
def user_pay_amount():
	data={}
	lid=request.args['lid']
	oid=request.args['oid']
	amount=request.args['amount']
	q="insert into payment values(null,'%s','sparepartbuy','%s',curdate())"%(oid,amount)
	insert(q)
	z="update `order` set status='paid' where order_id='%s'"%(oid)
	update(z)
	data['status']="success"
	return str(data)

@api.route('/user_pay_recharge_amount',methods=['get','post'])
def user_pay_recharge_amount():
	data={}
	lid=request.args['lid']
	rid=request.args['rid']
	amount=request.args['amount']
	q="insert into payment values(null,'%s','rechargerequest','%s',curdate())"%(rid,amount)
	insert(q)
	
	data['status']="success"
	return str(data)	

# @api.route('/user_view_oredereditem',methods=['get','post'])
# def user_view_oredereditem():
# 	data={}
# 	lid=request.args['lid']
# 	q="select * from `order` inner join sparepart using(sparepart_id) inner join user using(user_id) inner join orderdetail using(order_id) inner join product using(product_id) where user_id=(select user_id from user where login_id='%s')"%(lid)
# 	res=select(q)
# 	if res:
# 		data['status']="success"
# 		data['data']=res
# 	else:
# 		data['status']="failed"
		
# 	return str(data)

# @api.route('/user_view_status',methods=['get','post'])
# def user_view_status():
# 	data={}
# 	lid=request.args['lid']
# 	q="select * from `order` inner join sparepart using(sparepart_id) inner join user using(user_id) inner join orderdetail using(order_id) inner join product using(product_id) where user_id=(select user_id from user where login_id='%s')"%(lid)
# 	res=select(q)
# 	if res:
# 		data['status']="success"
# 		data['data']=res
# 	else:
# 		data['status']="failed"
		
# 	return str(data)

# @api.route('/user_rate_sparepart',methods=['get','post'])
# def user_rate_sparepart():

# 	data={}

# 	rating=request.args['rating']
# 	rid=request.args['rid']
# 	lid=request.args['lid']

# 	q="SELECT * FROM `rating` WHERE `user_id`=(SELECT `user_id` FROM `user` WHERE `login_id`='%s')"%(lid)
# 	res=select(q)
# 	if res:

# 		z="update rating set rating='%s',date='curdate()' where requested_id='%s' and user_id=(select user_id from user where login_id='%s')"%(rating,rid,lid)
# 		update(z)
# 		data['method'] = 'user_rate_sparepart'
# 		data['status'] = 'success'
# 	else:
# 		q="insert into rating values(null,(select user_id from user where login_id='%s'),'%s','%s',curdate())"%(lid,rid,rating)
# 		id=insert(q)
# 		if id>0:
# 			data['status'] = 'success'
			
# 		else:
# 			data['status'] = 'failed'
# 		data['method'] = 'user_rate_sparepart'
# 	return str(data)


# @api.route('/user_view_review_sparepart',methods=['get','post'])
# def user_view_review_sparepart():
# 	data = {}

# 	lid=request.args['lid']
	
	
# 	q="SELECT * FROM `rating` WHERE `user_id`=(SELECT `user_id` FROM `user` WHERE `login_id`='%s') "%(lid)
# 	print(q)
# 	result=select(q)
# 	if result:
# 		data['status'] = 'success'
# 		data['data'] = result[0]['rating']
		
		
# 	else:
# 		data['status'] = 'failed'
# 	data['method'] = 'user_view_review_sparepart'
# 	return str(data)


@api.route('/user_send_complaint')
def user_send_complaint():
	data={}
	lid=request.args['lid']
	complaint=request.args['complaint']
	q="INSERT INTO complaint VALUES(NULL,'%s','%s','pending',CURDATE(),'0')"%(lid,complaint)
	insert(q)
	data['status']="success"
	data['method']="user_send_complaint"
	return str(data)					

# @api.route('/deliveryboy_register')
# def deliveryboy_register():
# 	data={}
# 	firstname=request.args['firstname']
# 	lastname=request.args['lastname']
# 	place=request.args['place']	
# 	phone=request.args['phone']
# 	email=request.args['email']
# 	username=request.args['username']
# 	password=request.args['password']
# 	latitude=request.args['latitude']
# 	longitude=request.args['longitude']
# 	q="select * from login where username='%s' and password='%s'"%(username,password)
# 	res=select(q)
# 	if res:
# 		data['status']="duplicate"
# 	else:
# 		q="insert into login values(null,'%s','%s','delivery_boy')"%(username,password)
# 		id=insert(q)
# 		z="insert into sparepart values(null,'%s','%s','%s','%s','%s','%s','%s','%s')"%(id,firstname,lastname,place,phone,email,latitude,longitude)
# 		insert(z)
# 		data['status']="success"
# 	data['method']="spare_registration"	
# 	return str(data)

# @api.route('/deliveryboy_view_profile')
# def deliveryboy_view_profile():
# 	data={}
# 	lid=request.args['lid']
# 	q="select * from sparepart where sparepart_id=(select sparepart_id from sparepart where login_id='%s')"%(lid)
# 	res=select(q)
# 	if res:
# 		data['status']="success"
# 		data['data']=res
# 	else:
# 		data['status']="failed"
# 	data['method']="deliveryboy_view_profile"	
# 	return str(data)

# @api.route('/deliveryboy_update_profile')
# def deliveryboy_update_profile():
# 	data={}
# 	lid=request.args['lid']
# 	firstname=request.args['firstname']
# 	lastname=request.args['lastname']
# 	place=request.args['place']
# 	phone=request.args['phone']
# 	email=request.args['email']
# 	latitude=request.args['latitude']
# 	longitude=request.args['longitude']
# 	q="update sparepart set firstname='%s',lastname='%s',place='%s',phone='%s',email='%s',latitude='%s',longitude='%s' where sparepart_id=(select sparepart_id from sparepart where login_id='%s')"%(firstname,lastname,place,phone,email,latitude,longitude,lid)
# 	update(q)
# 	data['status']="success"
# 	data['method']="deliveryboy_update_profile"
# 	return str(data)

# @api.route('/deliveryboy_add_product',methods=['get','post'])
# def deliveryboy_add_product():
# 	data={}
# 	lid=request.form['lid']
# 	productname=request.form['productname']
# 	quantity=request.form['quantity']
# 	amount=request.form['amount']
# 	image=request.files['image']
# 	path="static/uploads/"+str(uuid.uuid4())+image.filename
# 	image.save(path)
# 	q="insert into product values(null,(select sparepart_id from sparepart where login_id='%s'),'%s','%s','%s','%s')" %(lid,productname,quantity,amount,path)
# 	id=insert(q)
	
# 	data['status']="success"
# 	return str(data)

# @api.route('/deliveryboy_view_request')
# def deliveryboy_view_request():
# 	data={}
# 	lid=request.args['lid']
# 	q="select * from `order` inner join sparepart using(sparepart_id) inner join user using(user_id) inner join orderdetail using(order_id) inner join product using(product_id) where `sparepart`.`sparepart_id`=(select sparepart_id from sparepart where login_id='%s')"%(lid)
# 	res=select(q)
# 	if res:
# 		data['status']="success"
# 		data['data']=res
# 	else:
# 		data['status']="failed"
		
# 	return str(data)

# @api.route('/deliveryboy_accept_request',methods=['get','post'])
# def deliveryboy_accept_request():
# 	data={}
# 	lid=request.args['lid']
# 	oid=request.args['oid']
# 	q="update `order` set status='accept' where order_id='%s'"%(oid)
# 	update(q)
# 	data['status']="success"
	
# 	return str(data)	

# @api.route('/deliveryboy_reject_request',methods=['get','post'])
# def deliveryboy_reject_request():
# 	data={}
# 	lid=request.args['lid']
# 	oid=request.args['oid']
# 	q="update `order` set status='reject' where order_id='%s'"%(oid)
# 	update(q)
# 	data['status']="success"

# 	return str(data)

# @api.route('/deliveryboy_send_complaint')
# def deliveryboy_send_complaint():
# 	data={}
# 	lid=request.args['lid']
# 	complaint=request.args['complaint']
# 	q="insert into complaint values(null,(select sparepart_id from sparepart where login_id='%s'),'%s','pending',curdate())"%(lid,complaint)
# 	insert(q)
# 	data['status']="success"
# 	data['method']="deliveryboy_send_complaint"
# 	return str(data)
				



# @api.route('/deliveryboy_view_customer',methods=['get','post'])
# def deliveryboy_view_customer():
# 	data={}
# 	uid=request.args['uid']
# 	q="select * from user where user_id='%s'"%(uid)
# 	res=select(q)
# 	if res:
# 		data['status']="success"
# 		data['data']=res
# 	else:
# 		data['status']="failed"
		
# 	return str(data)

@api.route('/user_view_vehicle',methods=['get','post'])
def user_view_vehicle():
	data={}
	lid=request.args['lid']
	q="SELECT * FROM `vehicle` WHERE user_id=(SELECT user_id FROM `user` WHERE login_id='%s' )"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="user_view_vehicle"
	return str(data)

# @api.route('/deliveryboy_set_to_delivery',methods=['get','post'])
# def deliveryboy_set_to_delivery():
# 	data={}
# 	oid=request.args['oid']
	
# 	q="update `order` set status='delivery' where order_id='%s'"%(oid)
# 	update(q)
# 	data['status']="success"
# 	data['method']="deliveryboy_set_to_delivery"	
# 	return str(data)					
					
@api.route('/user_manage_vehicle',methods=['get','post'])
def user_manage_vehicle():
	data={}
	lid=request.args['lid']
	types=request.args['type']
	vehicle=request.args['vehicle']
	q="INSERT INTO `vehicle` VALUES(NULL,'%s',(SELECT user_id FROM `user` WHERE login_id='%s'),'%s')"%(vehicle,lid,types)
	update(q)
	data['status']="success"
	data['method']="user_manage_vehicle"
	return str(data)					

@api.route('/bunk_register')
def bunk_register():
	data={}
	name=request.args['firstname']
	place=request.args['place']
	latitude=request.args['latitude']
	longitude=request.args['longitude']
	username=request.args['username']
	password=request.args['password']
	z="insert into login values(null,'%s','%s','bunk')"%(username,password)
	id=insert(z)
	q="insert into bunk values(null,'%s','%s','%s','%s','%s')"%(id,name,place,latitude,longitude)
	insert(q)
	data['status']="success"
	data['method']="bunk_register"
	return str(data)

@api.route('/user_delete_vehicle')
def user_delete_vehicle():
	data={}
	vehicle_id=request.args['vehicle_id']
	z="DELETE FROM `vehicle` WHERE `vehicle_id`='%s'"%(vehicle_id)
	delete(z)
	data['status']="success"
	data['method']="user_delete_vehicle"
	return str(data)	

@api.route('/user_update_vehicle')
def user_update_vehicle():
	data={}
	vehicle_id=request.args['vehicle_id']
	vehicle_name=request.args['vehicle_name']
	types=request.args['type']
	q="update vehicle set vehicle_name='%s',type='%s' where `vehicle_id`='%s'"%(vehicle_name,types,vehicle_id)
	update(q)
	data['status']="success"
	data['method']="user_update_vehicle"
	return str(data)	
	
@api.route('/user_view_vehicle_for_update',methods=['get','post'])
def user_view_vehicle_for_update():
	data={}
	lid=request.args['lid']
	q="SELECT * FROM `vehicle` WHERE user_id=(SELECT user_id FROM `user` WHERE login_id='%s' )"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="user_view_vehicle_for_update"
	return str(data)	

@api.route('/user_request_bunk_select_type',methods=['get','post'])
def user_request_bunk_select_type():
	data={}
	lid=request.args['lid']
	bid=request.args['bid']
	q="SELECT * FROM `vehicle` WHERE user_id=(SELECT user_id FROM `user` WHERE login_id='%s')"%(lid)
	res=select(q)
	q="SELECT * FROM `my_type` INNER JOIN `station_type` USING(`type_id`) WHERE `bunk_id`='%s'"%(bid)
	ress=select(q)
	if res:
		data['status']="success"
		data['veh']=res
		data['typ']=ress
	else:
		data['status']="failed"
	data['method']="user_request_bunk_select_type"
	return str(data)	

@api.route('/user_request_mechanic_select_type',methods=['get','post'])
def user_request_mechanic_select_type():
	data={}
	lid=request.args['lid']
	q="SELECT * FROM `vehicle` WHERE user_id=(SELECT user_id FROM `user` WHERE login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['veh']=res
	else:
		data['status']="failed"
	data['method']="user_request_mechanic_select_type"
	return str(data)	

@api.route('/user_request_mechanic',methods=['get','post'])
def user_request_mechanic():
	data={}
	vehi=request.args['vid']
	lid=request.args['lid']
	mid=request.args['mid']
	date=request.args['date']
	q="insert into mechanicrequest values(null,(select user_id from user where login_id='%s'),'%s','pending','%s','pending','%s')"%(lid,mid,date,vehi)
	insert(q)
	data['status']="success"
	data['method']="user_request_mechanic"	
	return str(data)	

@api.route('/user_pay_mechanic',methods=['get','post'])
def user_pay_mechanic():
	data={}
	lid=request.args['lid']
	rid=request.args['rid']
	amount=request.args['amount']
	q="insert into payment values(null,'%s','mechanicrequest','%s',curdate())"%(rid,amount)
	insert(q)
	data['status']="success"
	return str(data)	

	
@api.route('/user_view_service_center',methods=['get','post'])
def user_view_service_center():
	data={}
	lati=request.args['lati']
	longi=request.args['longi']
	q="SELECT *,(3959 * ACOS ( COS ( RADIANS('%s') ) * COS( RADIANS( latitude) ) * COS( RADIANS( longitude ) - RADIANS('%s') ) + SIN ( RADIANS('%s') ) * SIN( RADIANS(latitude ) ))) AS user_distance from service_center  ORDER BY user_distance ASC" % (lati,longi,lati)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="user_view_service_center"		
	return str(data)	

@api.route('/user_send_complaint_bunk')
def user_send_complaint_bunk():
	data={}
	lid=request.args['lid']
	complaint=request.args['complaint']
	bunkid=request.args['bunkid']
	q="INSERT INTO complaint VALUES(NULL,'%s','%s','pending',CURDATE(),'%s')"%(lid,complaint,bunkid)
	insert(q)
	data['status']="success"
	data['method']="user_send_complaint_bunk"
	return str(data)
