package com.example.FareJudgeApp;

public class Details {

	

		private String numberid;
		private String name;
		private String establishmenttype;
		private String typeoffoodserved;
		private String location;
		private String phonenumber;
		
		
		//Details(); 
		public Details (String numberid, String name, String establishmenttype,
				String typeoffoodserved, String location, String phonenumber) {
			this.numberid = numberid;
			this.name = name;
			this.establishmenttype = establishmenttype;
			this.typeoffoodserved = typeoffoodserved;
			this.location = location;
			this.phonenumber = phonenumber;
			
		}


		public String getnumberid() {
			return numberid;
		}

		public void setnumberid(String numberid) {
			this.numberid = numberid;
		}
		
		public String getname() {
			return name;
		}

		public void setname(String name) {
			this.name = name;
		}
		public String getestablishmenttype() {
			return establishmenttype;
		}

		public void setestablishmenttype(String establishmenttype) {
			this.establishmenttype = establishmenttype;
		}
		
		public String gettypeoffoodserved() {
			return typeoffoodserved;
		}

		public void settypeoffoodserved(String typeoffoodserved) {
			this.typeoffoodserved = typeoffoodserved;
		}
		public String getlocation() {
			return location;
		}

		public void setlocation(String location) {
			this.location = location;
		}
		public String getphonenumber() {
			return phonenumber;
		}

		public void setphonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		
	}

