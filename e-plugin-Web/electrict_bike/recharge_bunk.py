from flask import *
from database import *
bunk=Blueprint('bunk',__name__)

@bunk.route('/bunk_home')
def bunk_home():
    return render_template('bunk_home.html')
    
@bunk.route('/bunk_view_type',methods=['get','post'])
def bunk_view_type():
    data={}
    qry="SELECT * FROM `station_type` WHERE `type_id` NOT IN(SELECT `type_id` FROM `my_type` WHERE `bunk_id`='%s')"%(session['bunk_id'])
    ss=data['type']=select(qry)
    # if ss:
    return render_template('bunk_view_types.html',data=data)
    # else:
    #     return redirect(url_for('bunk.bunk_home'))	
        
        
@bunk.route('/bunk_add_as_service',methods=['get','post'])
def bunk_add_as_service():
    
    type_id=request.args['type_id']
    q="INSERT INTO `my_type` VALUES(NULL,'%s','%s')"%(session['bunk_id'],type_id)
    q=insert(q)
    if q:
        flash("Added as your Service")
    return redirect(url_for('bunk.bunk_view_type'))	


@bunk.route('/Manage_bunk_profile',methods=['get','post'])
def Manage_bunk_profile():
    data={}
    qry="select * from bunk where `bunk_id`='%s'"%(session['bunk_id'])
    data['pro']=select(qry)
    if 'submit' in request.form:
        firstname=request.form['firstname']
        place=request.form['place']
        phone=request.form['phone']
        email=request.form['email']
        latitude=request.form['latitude']
        longitude=request.form['longitude']
        q="update bunk set name='%s',place='%s',latitude='%s',longitude='%s',phone='%s',email='%s' where bunk_id='%s'"%(firstname,place,latitude,longitude,phone,email,session['bunk_id'])
        update(q)
        flash("Updated successfully")
        return redirect(url_for('bunk.Manage_bunk_profile'))
    return render_template('bunk_manage_profile.html',data=data)
   

     
@bunk.route('/bunk_view_request',methods=['get','post'])
def bunk_view_request():
    data={}
    qry="SELECT * FROM `rechargerequest` INNER JOIN `my_type` USING(`my_type_id`) INNER JOIN `user` USING(`user_id`) WHERE `my_type`.bunk_id='%s'"%(session['bunk_id'])
    data['req']=select(qry)
    return render_template('bunk_view_request.html',data=data)
     
@bunk.route('/bunk_view_payment',methods=['get','post'])
def bunk_view_payment():
    data={}
    rrequest_id=request.args['rrequest_id']
    qry="SELECT * FROM `payment`  WHERE `requestedfor`='rechargerequest'  AND requested_id='%s'"%(rrequest_id)
    ss=data['pay']=select(qry)
    if not ss:
        flash('Not Payed')
        return redirect(url_for('bunk.bunk_view_request'))
    return render_template('bunk_view_payment.html',data=data)

@bunk.route('/bunk_send_service_charge',methods=['get','post'])
def bunk_send_service_charge():
    data={}
    if "rrequest_id" in request.args:
        session['rrequest_id']=request.args['rrequest_id']
    if "submit" in request.form:
        amnt=request.form['reply']
        qry="update rechargerequest set amount='%s',status='accepted' where rrequest_id='%s'"%(amnt,session['rrequest_id'])
        ss=data['pay']=update(qry)
        if  ss:
            flash('Sent Successfully')
            return redirect(url_for('bunk.bunk_view_request'))
    return render_template('bunk_send_service_charge.html')
     
   
@bunk.route('/bunk_view_compliants',methods=['get','post'])
def bunk_view_compliants():
    data={}
    qry="SELECT * FROM `complaint` INNER JOIN `login` ON `complaint`.`receiver_id`=`login`.`login_id` WHERE login_id='%s'"%(session['lid'])
    data['req']=select(qry)
    return render_template('bunk_view_compliants.html',data=data)  

# @bunk.route('/bunk_view_type',methods=['get','post'])
# def bunk_view_type():
#     data={}
#     qry="SELECT * FROM `my_type` WHERE `bunk_id`='%s'"%(ssession['bunk_id'])
#     data['req']=select(qry)
#     return render_template('bunk_view_types.html',data=data)   
#  	