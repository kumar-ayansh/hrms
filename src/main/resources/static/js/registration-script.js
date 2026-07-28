// ===== HR SYNERGY REGISTRATION SCRIPT =====

(function() {
  const form = document.getElementById('regForm');
  if (!form) return;

  const emailInput = document.getElementById('email');
  const passwordInput = document.getElementById('password');
  const togglePasswordBtn = document.getElementById('togglePassword');
  const formAlert = document.getElementById('formAlert');
  const submitBtn = form.querySelector('.btn-submit-enquiry');
  
  function markField(input, valid) {
    if (!input) return;
    input.classList.remove('is-valid', 'is-invalid');
    if (valid === true) input.classList.add('is-valid');
    if (valid === false) input.classList.add('is-invalid');
  }
  
  if (togglePasswordBtn && passwordInput) {
    togglePasswordBtn.addEventListener('click', () => {
      const isPassword = passwordInput.type === 'password';
      passwordInput.type = isPassword ? 'text' : 'password';
      togglePasswordBtn.innerHTML = isPassword ?
        '<i class="bi bi-eye-slash-fill"></i>' :
        '<i class="bi bi-eye-fill"></i>';
    });
  }
  
  function showAlert(type, message) {
    if (!formAlert) return;
    formAlert.className = 'alert alert-' + type;
    formAlert.textContent = message;
    formAlert.classList.remove('d-none');
    formAlert.scrollIntoView({ behavior: 'smooth', block: 'center' });
  }
  
  form.addEventListener('submit', function(e) {
    e.preventDefault();
    
    if (formAlert) {
      formAlert.classList.add('d-none');
    }
    
    const fields = [
      'fullName', 'gender', 'contactNumber', 'email',
      'password', 'qualification', 'experience', 'skills', 'address'
    ];
    
    let firstInvalid = null;
    fields.forEach((id) => {
      const el = document.getElementById(id);
      if (el) {
        const valid = el.checkValidity();
        markField(el, valid ? null : false);
        if (!valid && !firstInvalid) firstInvalid = el;
      }
    });
    
    if (firstInvalid) {
      showAlert('danger', 'Please fill in all required fields correctly.');
      firstInvalid.focus();
      return;
    }
    
    if (submitBtn) {
      submitBtn.innerHTML = 'Submitting... <i class="bi bi-hourglass-split ms-1"></i>';
    }
    
    form.submit();
  });
})();