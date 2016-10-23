package server;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server {
	private final String idKey = "id"; 
	private final String resultKey = "result";
	private final String objectIdKey = "objectId";

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/fetchStudents")
	public String fetchStudents() {
		try {
			return CloudQuery.callCloudCodeFunction("fetchStudents", "");
		}
		catch(Exception e) {
			return e.toString();
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/fetchStudentWithId")
	public String fetchStudentWithId(@RequestParam(value=idKey) String id) {
		try {
			return CloudQuery.callCloudCodeFunction("fetchStudentWithId", "{\"" + idKey + "\": \"" + id + "\"}");
		}
		catch(Exception e) {
			return e.toString();
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/fetchStudentsForClassWithId")
	public String fetchStudentsForClassWithId(@RequestParam(value=idKey) String id) {
		try {
			return CloudQuery.callCloudCodeFunction("fetchStudentsForClassWithId", "{\"" + idKey + "\": \"" + id + "\"}");
		}
		catch(Exception e) {
			return e.toString();
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/fetchClassWithId")
	public String fetchClassWithId(@RequestParam(value=idKey) String id) {
		try {
			return CloudQuery.callCloudCodeFunction("fetchClassWithId", "{\"" + idKey + "\": \"" + id + "\"}");
		}
		catch(Exception e) {
			return e.toString();
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/fetchDepartmentForStudentWithId")
	public String fetchDepartmentForStudentWithId(@RequestParam(value=idKey) String id) {
		try {
			return CloudQuery.callCloudCodeFunction("fetchDepartmentForStudentWithId", "{\"" + idKey + "\": \"" + id + "\"}");
		}
		catch(Exception e) {
			return e.toString();
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/fetchClassesForStudentWithId")
	public String fetchClassesForStudentWithId(@RequestParam(value=idKey) String id) {
		try {
			return CloudQuery.callCloudCodeFunction("fetchClassesForStudentWithId", "{\"" + idKey + "\": \"" + id + "\"}");
		}
		catch(Exception e) {
			return e.toString();
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/fetchAvailableClassesForSecretaryWithId")
    public String fetchAvailableClassesForSecretaryWithId(@RequestParam(value=idKey) String id) {
    	try {
    		return CloudQuery.callCloudCodeFunction("fetchAvailableClassesForSecretaryWithId", "{\"" + idKey + "\": \"" + id + "\"}");
    	}
    	catch(Exception e) {
    		return e.toString();
    	}
    }

	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/fetchClassesForDepartmentWithId")
    public String fetchClassesForDepartmentWithId(@RequestParam(value=idKey) String id) {
    	try {
    		return CloudQuery.callCloudCodeFunction("fetchClassesForDepartmentWithId", "{\"" + idKey + "\": \"" + id + "\"}");
    	}
    	catch(Exception e) {
    		return e.toString();
    	}
    }

	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/fetchDepartments")
    public String fetchDepartments() {
    	try {
    		return CloudQuery.callCloudCodeFunction("fetchDepartments", "");
    	}
    	catch(Exception e) {
    		return e.toString();
    	}
    }

	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/fetchSecretaryWithId")
    public String fetchSecretaryWithId(@RequestParam(value=idKey) String id) {
    	try {
    		return CloudQuery.callCloudCodeFunction("fetchSecretaryWithId", "{\"" + idKey + "\": \"" + id + "\"}");
    	}
    	catch(Exception e) {
    		return e.toString();
    	}
    }

	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/fetchTeacherWithId")
    public String fetchTeacherWithId(@RequestParam(value=idKey) String id) {
    	try {
    		return CloudQuery.callCloudCodeFunction("fetchTeacherWithId", "{\"" + idKey + "\": \"" + id + "\"}");
    	}
    	catch(Exception e) {
    		return e.toString();
    	}
    }

	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/fetchStudentsForSecretaryWithId")
    public String fetchStudentsForSecretaryWithId(@RequestParam(value=idKey) String id) {
    	try {
    		return CloudQuery.callCloudCodeFunction("fetchStudentsForSecretaryWithId", "{\"" + idKey + "\": \"" + id + "\"}");
    	}
    	catch(Exception e) {
    		return e.toString();
    	}
    }

	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/signupStudentWithIdForClassWithId")
    public String signupStudentWithIdForClassWithId(@RequestParam(value=idKey) String studentId, @RequestParam(value="classId") String classId) {
    	try {
    		String studentString = CloudQuery.callCloudCodeFunction("fetchStudentWithId", "{\"" + idKey + "\": \"" + studentId + "\"}");
    		String classString = CloudQuery.callCloudCodeFunction("fetchClassWithId", "{\"" + idKey + "\": \"" + classId + "\"}");
    		String secretaryString = CloudQuery.callCloudCodeFunction("fetchSecretaryForStudentWithId", "{\"" + idKey + "\": \"" + studentId + "\"}");
    		String classSecretaryString = CloudQuery.callCloudCodeFunction("fetchSecretaryForClassWithId", "{\"" + idKey + "\": \"" + classId + "\"}");
    		String docSecretaryString = CloudQuery.callCloudCodeFunction("fetchDocSecretaryForStudentWithId", "{\"" + idKey + "\": \"" + studentId + "\"}");
    		
    		Object obj;
    		JSONObject student = (JSONObject) JSONValue.parse(studentString);
    		student = (JSONObject) student.get(resultKey);
    		JSONObject clss = (JSONObject) JSONValue.parse(classString);
    		clss = (JSONObject) clss.get(resultKey);
    		JSONObject secretary = (JSONObject) JSONValue.parse(secretaryString);
    		secretary = (JSONObject) secretary.get(resultKey);
    		JSONObject classSecretary = (JSONObject) JSONValue.parse(classSecretaryString);
    		classSecretary = (JSONObject) classSecretary.get(resultKey);
    		JSONObject docSecretary = (JSONObject) JSONValue.parse(docSecretaryString);
    		docSecretary = (JSONObject) docSecretary.get(resultKey);
    		
    		obj = clss.get("isAvailable");
    		
    		if(obj instanceof Boolean) {
    			Boolean isAvailable = (Boolean) obj;
    			if(!isAvailable) {
    				return "{\"error\":\"Disciplina indisponível.\"}";
    			}
    		}
    	
    		obj = clss.get("requiredClasses");
    		
    		if(obj instanceof JSONArray) {
    			JSONArray requiredClasses = (JSONArray) obj;
    			if(requiredClasses.size() > 0) {
    				obj = student.get("coursedClasses");
        			if(obj instanceof JSONArray) {
        				JSONArray coursedClasses = (JSONArray) obj;
        				Iterator i = requiredClasses.iterator();
        				while(i.hasNext()) {
            				Boolean found = false;
        					String requiredClass = (String) i.next();
        					Iterator j = coursedClasses.iterator();
        					while(j.hasNext()) {
        						String coursedClass = (String) j.next();
        						if(coursedClass.compareTo(requiredClass) == 0) {
        							found = true;
        							break;
        						}
        					}
        					
        					if(!found) {
        						return "{\"error\":\"Pré-requisitos não atendidos.\"}";
        					}
        				}
        			}
        			else {
        				return "{\"error\":\"Pré-requisitos não atendidos.\"}";
        			}
    			}
    		}
    		
    		obj = clss.get("requiredCredit");
    		
    		if(obj instanceof Long) {
    			Long requiredCredit = (Long) obj;
    			if(requiredCredit > 0) {
    				obj = student.get("mandatoryCredits");
    				if(!(obj instanceof Long)) {
    					obj = new Long(0);
    				}
    				Long studentCredit = (Long) obj;
    				
    				if(studentCredit < requiredCredit) {
    					return "{\"error\":\"Pré-requisitos não atendidos.\"}";
    				}
    			}
    		}
    		
    		String secretaryId = (String) secretary.get(objectIdKey);
    		String classSecretaryId = (String) classSecretary.get(objectIdKey);
    		
<<<<<<< HEAD
    		if(secretaryId.compareTo(classSecretaryId) != 0) {
    			String docSecretaryId = (String) docSecretary.get(objectIdKey);
    			if(secretaryId.compareTo(docSecretaryId) == 0) {
    				obj = student.get("mandatoryCredits");
    				if(obj instanceof Integer) {
    					Integer credit = (Integer) obj;
    					if(credit < 170) {
    						return "{\"error\":\"Pré-requisitos não atendidos.\"}";
    					}
    				}
    				else {
    					return "{\"error\":\"Pré-requisitos não atendidos.\"}";
    				}
    			}
    			else {
    				return "{\"error\":\"Secretaria inválida.\"}";
=======
    		if(classSecretaryId.compareTo(secretaryId) != 0) {
    			obj = student.get("mandatoryCredits");
    			if(obj instanceof Long) {
    				Long credit = (Long) obj;
    				if(credit < 170) {
    					return "{\"error\":\"pre-requisitos não atendidos\"}";
    				}
    			}
    			else {
    				return "{\"error\":\"pre-requisitos não atendidos\"}";
>>>>>>> 7560be8a8559e73ff10fb9826c94f33ed763b072
    			}
    		}
    		
    		obj = student.get("coursedClasses");
			if(obj instanceof JSONArray) {
				JSONArray coursedClasses = (JSONArray) obj;
				Iterator i = coursedClasses.iterator();
				while(i.hasNext()) {
    				String coursedClassId = (String) i.next();
    				if(coursedClassId.compareTo(classId) == 0) {
    					return "{\"error\":\"Aluno já está matriculado na disciplina.\"}";
    				}
				}
			}

    		return "{\"result\":\"Aluno matriculado com sucesso.\"}";
    	}
    	catch(Exception e) {
    		return e.toString();
    	}
    }
}
