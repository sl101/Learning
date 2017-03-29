package ua.com.foxminded.serviceacc.model.enums;

public enum Level {
	Applicant {
		public Level up() {
			return Beginner;
		}
	},
	Beginner {
		public Level up() {
			return Regular;
		}
	},
	Regular{
	public Level up() { 
		return Graduate; 
		}

	},
	Graduate {
		public Level up() {
			return Graduate;
		}
	};

	public abstract Level up();
}
