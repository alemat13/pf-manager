import { useRef, useState } from "react";

type Validation = {
  callback: (value: string) => boolean;
  errorMessage: string;
};


const useInput = (
  id: string,
  type: "text" | "password",
  label: string,
  validation?: Validation[]
) => {
  const ref = useRef<HTMLInputElement>(null);
  const [status, setStatus] = useState<"idle" | "valid" | "error">("idle");
  const [errors, setErrors] = useState<string[]>([]);
  const onChangeHandler = () => {
    const newValue = ref.current!.value!;
    setStatus("valid");
    setErrors([]);
    if (validation) {
      validation.forEach((v) => {
        if (!v.callback(newValue)) {
          setStatus(() => "error");
          setErrors((state) => [...state, v.errorMessage]);
        }
      });
    }
  };

  return {
    id,
    type,
    label,
    validate: validation,
    ref,
    onChangeHandler,
    status,
    errors,
  };
};

export default useInput;
