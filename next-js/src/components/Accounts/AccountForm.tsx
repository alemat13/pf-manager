import useInput from "@/hooks/use-input";
import Account from "@/models/account";

const isNotEmptyValidation = {
  callback: (value: string) => value.trim().length > 0,
  errorMessage: "Value must not be empty",
};

const AccountForm: React.FC<{ account?: Account }> = ({ account }) => {
  const titleInput = useInput("title", "text", "Account name", [
    isNotEmptyValidation,
  ]);
  const bankNameInput = useInput("bankName", "text", "Bank Name", [
    isNotEmptyValidation,
  ]);

  return (
    <ul>
      <form onSubmit={() => {}}>
        {[titleInput, bankNameInput].map(
          ({ id, type, label, onChangeHandler, ref, status, errors }) => {
            return (
              <div key={id}>
                <label htmlFor={id} style={{color: status === "error" ? 'red' : 'black'}}>{label}</label>
                <input
                  type={type}
                  id={id}
                  ref={ref}
                  onChange={onChangeHandler}
                />
                {errors && errors.join('<br/>')}
              </div>
            );
          }
        )}
        <input type='submit' value={account ? 'Save' : 'Create'}/>
      </form>
    </ul>
  );
};

export default AccountForm;
