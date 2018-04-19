import com.egis.DocumentModel
import com.egis.data.party.User
import com.egis.transnet.sap.Employee
import com.egis.utils.ValidationUtils

User lineManager = Employee.find(doc.signature().savedValues.manager_sap_no)?.createOrGetUser()
ValidationUtils.notNull(lineManager, "Cannot find employee with SAP No: " + doc.signature().savedValues.manager_sap_no)
DocumentModel doc = doc;
doc.signature().assignRole("_manager_", lineManager.name);
doc.workflow().set("_manager_", lineManager.name);
doc.parties().add(lineManager)

