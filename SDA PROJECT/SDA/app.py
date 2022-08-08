
from importlib.metadata import requires
from itertools import product
from pickletools import read_uint1
from sqlite3 import Cursor
from unicodedata import name
from flask import Flask, render_template, jsonify, request,redirect,url_for
from flask_sqlalchemy import SQLAlchemy

local_server = True
app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI']='mysql://root:1234@localhost/sdaproject'
db=SQLAlchemy(app)

    


class info(db.Model):
    id = db.Column(db.Integer,primary_key=True)
    fname = db.Column(db.String(25), nullable=False)
    lname = db.Column(db.String(25), nullable=False)
    email=db.Column(db.String(30),nullable=False)
    contactno=db.Column(db.String(20),nullable=False)
    passwords=db.Column(db.String(25),nullable=False)


@app.route("/",methods=['GET','POST'])
def login():
    if (request.method=="POST"):
        email = request.form.get('email')
        userpass = request.form.get('password')
        temp = info.query.filter_by(email=email).first()
        if email == temp.email and userpass == temp.passwords :
           return render_template('store.html',temp=temp),404
        else:
            return '<h1 style="text-align: center;">Sign Up First</h1>'
    return render_template('login.html'),404



@app.route("/Signup", methods=['POST','GET'])
def Signup():
    if request.method =="POST":
        fname = request.form.get('fname')
        lname = request.form.get('lname')
        email = request.form.get('email')
        contact = request.form.get('contactno')
        passwords = request.form.get('password')
        entry = info(fname=fname,lname=lname,email=email,contactno=contact,passwords=passwords)
        db.session.add(entry)
        db.session.commit()
        return '<h3 style="text-align: center;">Account created Succesfully !</h3>'
    return render_template('Signup.html'),404





class orders(db.Model):
    id = db.Column(db.Integer,primary_key=True)
    productname = db.Column(db.String(100),nullable=False)
    price = db.Column(db.Integer,nullable=False)
    quantity=db.Column(db.Integer,nullable=False)

@app.route("/", methods=['POST','GET'])     
def productbuy():
    if request.method =="POST":
        productname = request.form.get('product')
        price = request.form.get('price')
        quantity = request.form.get('quantity')
        entry = orders(productname=productname,price=price,quantity=quantity)
        db.session.add(entry)
        db.session.commit()
        return '<h3>data save Succesfully</h3>'
    return render_template('store1.html'),404    

@app.route("/admin",methods=['GET','POST'])
def admin():
    return render_template('admin.html'),404
    
if __name__ == '__main__':
    app.run(debug=True)
