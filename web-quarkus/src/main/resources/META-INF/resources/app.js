(function(){
  const apiBase = '/api/products';

  function $(sel, root = document) { return root.querySelector(sel); }

  async function listProducts(){
    try{
      const res = await fetch(apiBase);
      if(!res.ok) throw new Error('Failed to fetch');
      const data = await res.json();
      const tbody = $('#productsTable tbody');
      tbody.innerHTML = '';
      data.forEach(p => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td>${p.id ?? ''}</td>
          <td>${escapeHtml(p.name)}</td>
          <td>${p.energyKj}</td>
          <td>${p.sugar}</td>
          <td>${p.saturatedFat}</td>
          <td>${p.sodium}</td>
          <td>${p.fiber}</td>
          <td>${p.protein}</td>
          <td>${p.fruitsPercentage}</td>
          <td>${p.nutriScore ?? ''}</td>
        `;
        tbody.appendChild(tr);
      });
    }catch(e){
      console.error(e);
    }
  }

  function escapeHtml(s){
    if(!s) return '';
    return s.replace(/[&<>\"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
  }

  async function createProduct(form){
    const data = {
      name: form.name.value,
      energyKj: parseFloat(form.energyKj.value),
      sugar: parseFloat(form.sugar.value),
      saturatedFat: parseFloat(form.saturatedFat.value),
      sodium: parseFloat(form.sodium.value),
      fiber: parseFloat(form.fiber.value),
      protein: parseFloat(form.protein.value),
      fruitsPercentage: parseFloat(form.fruitsPercentage.value)
    };

    try{
      const res = await fetch(apiBase, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
      });
      if(!res.ok){
        const txt = await res.text();
        throw new Error(txt || 'Failed to create');
      }
      const created = await res.json();
      const out = document.getElementById('createResult');
      out.textContent = `Created product "${created.name}" — NutriScore: ${created.nutriScore}`;
      form.reset();
      listProducts();
    }catch(e){
      const out = document.getElementById('createResult');
      out.textContent = 'Error: ' + e.message;
      console.error(e);
    }
  }

  document.addEventListener('DOMContentLoaded', ()=>{
    listProducts();
    const form = document.getElementById('productForm');
    form.addEventListener('submit', e=>{
      e.preventDefault();
      createProduct(form);
    });
    document.getElementById('refresh').addEventListener('click', ()=> listProducts());
  });
})();

