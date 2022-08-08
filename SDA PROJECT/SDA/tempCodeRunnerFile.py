 id = db.Column(db.Integer,primary_key=True)
    productname = db.Column(db.String(100),nullable=False)
    price = db.Column(db.Integer,nullable=False)
    quantity=db.Column(db.Integer,nullable=False)