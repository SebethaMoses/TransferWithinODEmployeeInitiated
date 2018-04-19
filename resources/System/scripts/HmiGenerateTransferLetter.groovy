
import com.egis.DocumentModel
import com.egis.utils.DateUtils
import com.egis.Repository
import com.egis.Session
import com.egis.kernel.Kernel
import com.egis.utils.Handler

import com.egis.data.Form
import com.egis.kernel.db.DbManager

DocumentModel doc = doc
def values = doc.signature().getSavedValues()
Repository repo = Kernel.get(Repository.class)

DbManager db = Kernel.get(DbManager.class)
Form form = db.resolve(Form.class, 'Transfer Letter Employee Initiated')

repo.runAsSystem({ Session session ->
    DocumentModel model = doc.session.spawnForm(form)
    Map formValues = [
            'name'    : values.employee_name,
            'employee_sap_no'  : values.sap_no,
            'designation': values.designation,
            'center'         :values.center,
            'employee_name' : values.employee_name,
            'grade'			:values.grade,
            'approver_label' :values.approver_label
       
    ]
    model.metadata().set(formValues)
    model.signature().setDefaultValues(formValues)
    model.allocate('TransferLetterWithinOD')

} as Handler<Session>)
