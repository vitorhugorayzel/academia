```jsx
import { useState, useEffect, useRef } from "react";
import {
  LayoutDashboard,
  Users,
  Dumbbell,
  CreditCard,
  FileUp,
  Search,
  Plus,
  X,
  CheckCircle2,
  Clock,
  AlertCircle,
  UploadCloud,
  FileText,
  MoreVertical,
  TrendingUp,
  Calendar,
  ChevronRight,
  QrCode,
  Landmark,
  Receipt,
  ArrowLeft,
  Save,
  Trash2,
  Eye
} from "lucide-react";

export default function App() {
  const [activeTab, setActiveTab] = useState("dashboard");
  const [sidebarOpen, setSidebarOpen] = useState(true);
  const [modal, setModal] = useState(null);
  const [search, setSearch] = useState("");

  const [students, setStudents] = useState([
    { id: 1, name: "Ana Carla", plan: "Premium", status: "Ativo", since: "2024-03-10", phone: "(11) 98765-4321" },
    { id: 2, name: "Bruno Dias", plan: "Básico", status: "Ativo", since: "2025-01-15", phone: "(11) 91234-5678" },
    { id: 3, name: "Carolina M.", plan: "Premium", status: "Inadimplente", since: "2023-08-22", phone: "(11) 99876-5432" },
    { id: 4, name: "Diego R.", plan: "Intermediário", status: "Ativo", since: "2025-06-01", phone: "(11) 93456-7890" },
  ]);

  const [workouts, setWorkouts] = useState([
    { id: 1, title: "Hipertrofia A", studentId: 1, day: "Segunda", exercises: [{ name: "Supino reto", sets: 4, reps: "10-12" }, { name: "Crucifixo", sets: 3, reps: "12" }] },
    { id: 2, title: "Funcional Full", studentId: 2, day: "Terça", exercises: [{ name: "Burpee", sets: 4, reps: "15" }, { name: "Kettlebell swing", sets: 4, reps: "20" }] },
    { id: 3, title: "Costas e Bíceps", studentId: 1, day: "Quarta", exercises: [{ name: "Puxada frontal", sets: 4, reps: "10" }, { name: "Rosca direta", sets: 3, reps: "12" }] },
  ]);

  const [payments, setPayments] = useState([
    { id: 101, studentId: 3, studentName: "Carolina M.", value: 189.9, dueDate: "2026-09-15", status: "Pendente", method: null },
    { id: 102, studentId: 4, studentName: "Diego R.", value: 129.5, dueDate: "2026-09-20", status: "Pendente", method: null },
    { id: 103, studentId: 1, studentName: "Ana Carla", value: 249.9, dueDate: "2026-09-10", status: "Pago", method: "PIX", paidAt: "2026-09-09" },
    { id: 104, studentId: 2, studentName: "Bruno Dias", value: 99.0, dueDate: "2026-09-05", status: "Pago", method: "Cartão", paidAt: "2026-09-04" },
  ]);

  const [receipts, setReceipts] = useState([
    { id: "r1", name: "comprovante_ana_09_09.pdf", size: "124 KB", date: "09/09/2026", status: "Confirmado", type: "PDF" },
    { id: "r2", name: "comprovante_bruno_04_09.jpg", size: "2.1 MB", date: "04/09/2026", status: "Confirmado", type: "JPG" },
  ]);

  const navItems = [
    { id: "dashboard", label: "Dashboard", icon: LayoutDashboard },
    { id: "alunos", label: "Alunos", icon: Users },
    { id: "treinos", label: "Treinos", icon: Dumbbell },
    { id: "pagamentos", label: "Pagamentos", icon: CreditCard },
    { id: "comprovantes", label: "Comprovantes", icon: FileUp },
  ];

  const filteredStudents = students.filter((s) => s.name.toLowerCase().includes(search.toLowerCase()));

  const today = new Date("2026-09-19T21:30:01-03:00");

  function formatCurrency(v) {
    return v.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
  }

  function openModal(type, data = {}) {
    setModal({ type, data });
  }

  function closeModal() {
    setModal(null);
  }

  // Dashboard
  const DashboardView = () => {
    const totalReceita = payments.filter((p) => p.status === "Pago").reduce((a, b) => a + b.value, 0);
    const pendentes = payments.filter((p) => p.status === "Pendente").length;
    const ativos = students.filter((s) => s.status === "Ativo").length;

    return (
      <div className="space-y-6">
        <div className="relative overflow-hidden rounded-2xl border border-slate-800 bg-slate-900 p-8">
          <img
            src="https://images.pexels.com/photos/6046979/pexels-photo-6046979.png?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
            alt="Bright spacious gym"
            className="absolute inset-0 h-full w-full object-cover opacity-20"
          />
          <div className="relative z-10">
            <div className="inline-flex items-center gap-2 rounded-full border border-emerald-500/30 bg-emerald-500/10 px-3 py-1 text-xs font-medium text-emerald-400">
              <Calendar className="h-3.5 w-3.5" />
              {today.toLocaleDateString("pt-BR", { weekday: "long", day: "numeric", month: "long", year: "numeric" })}
            </div>
            <h2 className="mt-4 text-3xl font-bold tracking-tight text-white">Painel da Academia</h2>
            <p className="mt-2 max-w-xl text-slate-300">Acompanhe treinos, receitas e envios de comprovantes em um só lugar.</p>
          </div>
        </div>

        <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-4">
          <div className="rounded-xl border border-slate-800 bg-slate-900 p-5">
            <div className="flex items-center justify-between">
              <div className="text-sm text-slate-400">Alunos ativos</div>
              <Users className="h-5 w-5 text-emerald-500" />
            </div>
            <div className="mt-2 text-2xl font-bold text-white">{ativos}</div>
            <div className="mt-1 text-xs text-slate-500">de {students.length} cadastrados</div>
          </div>
          <div className="rounded-xl border border-slate-800 bg-slate-900 p-5">
            <div className="flex items-center justify-between">
              <div className="text-sm text-slate-400">Receita do mês</div>
              <TrendingUp className="h-5 w-5 text-emerald-500" />
            </div>
            <div className="mt-2 text-2xl font-bold text-white">{formatCurrency(totalReceita)}</div>
            <div className="mt-1 text-xs text-emerald-400">Baseado em pagamentos confirmados</div>
          </div>
          <div className="rounded-xl border border-slate-800 bg-slate-900 p-5">
            <div className="flex items-center justify-between">
              <div className="text-sm text-slate-400">Mensalidades pendentes</div>
              <AlertCircle className="h-5 w-5 text-amber-500" />
            </div>
            <div className="mt-2 text-2xl font-bold text-white">{pendentes}</div>
            <div className="mt-1 text-xs text-slate-500">Aguardando quitação</div>
          </div>
          <div className="rounded-xl border border-slate-800 bg-slate-900 p-5">
            <div className="flex items-center justify-between">
              <div className="text-sm text-slate-400">Treinos ativos</div>
              <Dumbbell className="h-5 w-5 text-sky-500" />
            </div>
            <div className="mt-2 text-2xl font-bold text-white">{workouts.length}</div>
            <div className="mt-1 text-xs text-slate-500">Vinculados a alunos</div>
          </div>
        </div>

        <div className="grid grid-cols-1 gap-6 lg:grid-cols-3">
          <div className="rounded-xl border border-slate-800 bg-slate-900 p-5 lg:col-span-2">
            <h3 className="text-lg font-semibold text-white">Últimos pagamentos</h3>
            <div className="mt-4 space-y-3">
              {payments.slice(0, 4).map((p) => (
                <div key={p.id} className="flex items-center justify-between rounded-lg border border-slate-800 bg-slate-950/50 p-3">
                  <div className="flex items-center gap-3">
                    <div className={`flex h-8 w-8 items-center justify-center rounded-full ${p.status === "Pago" ? "bg-emerald-500/10 text-emerald-400" : "bg-amber-500/10 text-amber-400"}`}>
                      {p.status === "Pago" ? <CheckCircle2 className="h-4 w-4" /> : <Clock className="h-4 w-4" />}
                    </div>
                    <div>
                      <div className="text-sm font-medium text-white">{p.studentName}</div>
                      <div className="text-xs text-slate-500">Vencimento {p.dueDate.split("-").reverse().join("/")}</div>
                    </div>
                  </div>
                  <div className="text-right">
                    <div className="text-sm font-semibold text-white">{formatCurrency(p.value)}</div>
                    <div className={`text-xs ${p.status === "Pago" ? "text-emerald-400" : "text-amber-400"}`}>{p.status}</div>
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="space-y-4">
            <div className="rounded-xl border border-slate-800 bg-slate-900 p-5">
              <h3 className="text-lg font-semibold text-white">Equipamentos</h3>
              <div className="mt-4 grid grid-cols-2 gap-3">
                <img
                  src="https://images.pexels.com/photos/3916766/pexels-photo-3916766.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
                  alt="Heavy dumbbells on a gym rack"
                  className="h-24 w-full rounded-lg object-cover"
                />
                <img
                  src="https://images.pexels.com/photos/38882512/pexels-photo-38882512.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
                  alt="Modern indoor cycling equipment"
                  className="h-24 w-full rounded-lg object-cover"
                />
                <img
                  src="https://images.pexels.com/photos/6050745/pexels-photo-6050745.png?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
                  alt="Gym machine for weightlifting"
                  className="h-24 w-full rounded-lg object-cover"
                />
                <img
                  src="https://images.pexels.com/photos/8933584/pexels-photo-8933584.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
                  alt="Stationary exercise bikes"
                  className="h-24 w-full rounded-lg object-cover"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  };

  // Alunos
  const AlunosView = () => (
    <div className="space-y-4">
      <div className="flex flex-col gap-4 sm:flex-row sm:items-center sm:justify-between">
        <h2 className="text-xl font-bold text-white">Alunos</h2>
        <div className="flex items-center gap-3">
          <div className="relative">
            <Search className="absolute left-2.5 top-2.5 h-4 w-4 text-slate-500" />
            <input
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              placeholder="Buscar aluno..."
              className="rounded-lg border border-slate-800 bg-slate-900 py-2 pl-9 pr-3 text-sm text-white placeholder-slate-500 outline-none focus:border-emerald-500"
            />
          </div>
          <button
            onClick={() => openModal("newStudent")}
            className="inline-flex items-center gap-2 rounded-lg bg-emerald-600 px-3 py-2 text-sm font-medium text-white hover:bg-emerald-500"
          >
            <Plus className="h-4 w-4" /> Novo
          </button>
        </div>
      </div>

      <div className="overflow-hidden rounded-xl border border-slate-800 bg-slate-900">
        <table className="w-full text-left text-sm">
          <thead className="bg-slate-950/50 text-slate-400">
            <tr>
              <th className="px-4 py-3 font-medium">Nome</th>
              <th className="px-4 py-3 font-medium">Plano</th>
              <th className="px-4 py-3 font-medium">Status</th>
              <th className="px-4 py-3 font-medium">Desde</th>
              <th className="px-4 py-3 font-medium">Ações</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-800">
            {filteredStudents.map((s) => (
              <tr key={s.id} className="hover:bg-slate-950/30">
                <td className="px-4 py-3 text-white">{s.name}</td>
                <td className="px-4 py-3 text-slate-300">{s.plan}</td>
                <td className="px-4 py-3">
                  <span className={`inline-flex items-center rounded-full px-2 py-0.5 text-xs font-medium ${s.status === "Ativo" ? "bg-emerald-500/10 text-emerald-400" : "bg-red-500/10 text-red-400"}`}>
                    {s.status}
                  </span>
                </td>
                <td className="px-4 py-3 text-slate-400">{s.since.split("-").reverse().join("/")}</td>
                <td className="px-4 py-3">
                  <div className="flex items-center gap-2">
                    <button
                      onClick={() => openModal("assignWorkout", { studentId: s.id, studentName: s.name })}
                      className="rounded-md bg-slate-800 px-2 py-1 text-xs text-white hover:bg-slate-700"
                    >
                      Treino
                    </button>
                    <button
                      onClick={() => openModal("payForStudent", { studentId: s.id, studentName: s.name })}
                      className="rounded-md bg-slate-800 px-2 py-1 text-xs text-white hover:bg-slate-700"
                    >
                      Pagar
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {filteredStudents.length === 0 && (
          <div className="p-8 text-center text-sm text-slate-500">Nenhum aluno encontrado.</div>
        )}
      </div>
    </div>
  );

  // Treinos
  const TreinosView = () => (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h2 className="text-xl font-bold text-white">Treinos</h2>
        <button
          onClick={() => openModal("newWorkout")}
          className="inline-flex items-center gap-2 rounded-lg bg-emerald-600 px-3 py-2 text-sm font-medium text-white hover:bg-emerald-500"
        >
          <Plus className="h-4 w-4" /> Criar treino
        </button>
      </div>

      <div className="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3">
        {workouts.map((w) => {
          const st = students.find((s) => s.id === w.studentId);
          return (
            <div key={w.id} className="rounded-xl border border-slate-800 bg-slate-900 p-5">
              <div className="flex items-start justify-between">
                <div>
                  <h3 className="font-semibold text-white">{w.title}</h3>
                  <p className="text-xs text-slate-400">Aluno: {st?.name || "Não vinculado"} • {w.day}</p>
                </div>
                <Dumbbell className="h-5 w-5 text-slate-600" />
              </div>
              <ul className="mt-4 space-y-2">
                {w.exercises.map((ex, idx) => (
                  <li key={idx} className="flex items-center justify-between rounded-lg bg-slate-950/50 px-3 py-2 text-sm">
                    <span className="text-slate-300">{ex.name}</span>
                    <span className="text-xs text-slate-500">{ex.sets}x{ex.reps}</span>
                  </li>
                ))}
              </ul>
              <div className="mt-4 flex items-center gap-2">
                <img
                  src="https://images.pexels.com/photos/3931307/pexels-photo-3931307.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
                  alt="Woman exercising on fitness machine"
                  className="h-16 w-full rounded-lg object-cover opacity-80"
                />
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );

  // Pagamentos
  const PagamentosView = () => {
    const [filter, setFilter] = useState("Todos");
    const list = payments.filter((p) => (filter === "Todos" ? true : p.status === filter));
    return (
      <div className="space-y-4">
        <div className="flex flex-col gap-4 sm:flex-row sm:items-center sm:justify-between">
          <h2 className="text-xl font-bold text-white">Pagamentos</h2>
          <div className="flex items-center gap-2">
            {["Todos", "Pendente", "Pago"].map((f) => (
              <button
                key={f}
                onClick={() => setFilter(f)}
                className={`rounded-full px-3 py-1 text-xs font-medium ${filter === f ? "bg-emerald-600 text-white" : "border border-slate-800 bg-slate-900 text-slate-400 hover:text-white"}`}
              >
                {f}
              </button>
            ))}
          </div>
        </div>

        <div className="overflow-hidden rounded-xl border border-slate-800 bg-slate-900">
          <table className="w-full text-left text-sm">
            <thead className="bg-slate-950/50 text-slate-400">
              <tr>
                <th className="px-4 py-3 font-medium">Aluno</th>
                <th className="px-4 py-3 font-medium">Vencimento</th>
                <th className="px-4 py-3 font-medium">Valor</th>
                <th className="px-4 py-3 font-medium">Status</th>
                <th className="px-4 py-3 font-medium">Ação</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-800">
              {list.map((p) => (
                <tr key={p.id} className="hover:bg-slate-950/30">
                  <td className="px-4 py-3 text-white">{p.studentName}</td>
                  <td className="px-4 py-3 text-slate-400">{p.dueDate.split("-").reverse().join("/")}</td>
                  <td className="px-4 py-3 text-white">{formatCurrency(p.value)}</td>
                  <td className="px-4 py-3">
                    <span className={`inline-flex items-center rounded-full px-2 py-0.5 text-xs font-medium ${p.status === "Pago" ? "bg-emerald-500/10 text-emerald-400" : "bg-amber-500/10 text-amber-400"}`}>
                      {p.status}
                    </span>
                  </td>
                  <td className="px-4 py-3">
                    {p.status === "Pendente" ? (
                      <button
                        onClick={() => openModal("pay", { payment: p })}
                        className="inline-flex items-center gap-1 rounded-md bg-emerald-600 px-2 py-1 text-xs font-medium text-white hover:bg-emerald-500"
                      >
                        <CreditCard className="h-3 w-3" /> Pagar agora
                      </button>
                    ) : (
                      <span className="inline-flex items-center gap-1 text-xs text-emerald-400">
                        <CheckCircle2 className="h-3 w-3" /> {p.method}
                      </span>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          {list.length === 0 && <div className="p-8 text-center text-sm text-slate-500">Nenhum pagamento encontrado.</div>}
        </div>
      </div>
    );
  };

  // Comprovantes
  const ComprovantesView = () => {
    const fileInputRef = useRef(null);

    function handleUpload(e) {
      const file = e.target.files?.[0];
      if (!file) return;
      const newReceipt = {
        id: Math.random().toString(36).slice(2),
        name: file.name,
        size: file.size > 1024 * 1024 ? `${(file.size / 1024 / 1024).toFixed(1)} MB` : `${Math.round(file.size / 1024)} KB`,
        date: today.toLocaleDateString("pt-BR"),
        status: "Processando...",
        type: file.name.split(".").pop().toUpperCase(),
      };
      setReceipts((prev) => [newReceipt, ...prev]);
      setTimeout(() => {
        setReceipts((prev) => prev.map((r) => (r.id === newReceipt.id ? { ...r, status: "Confirmado" } : r)));
      }, 2000);
      if (fileInputRef.current) fileInputRef.current.value = "";
    }

    return (
      <div className="space-y-4">
        <h2 className="text-xl font-bold text-white">Comprovantes</h2>

        <div
          onClick={() => fileInputRef.current?.click()}
          className="cursor-pointer rounded-xl border-2 border-dashed border-slate-700 bg-slate-900 p-8 text-center transition-colors hover:border-emerald-500/50 hover:bg-slate-800/50"
        >
          <input ref={fileInputRef} type="file" className="hidden" onChange={handleUpload} />
          <UploadCloud className="mx-auto h-8 w-8 text-slate-500" />
          <p className="mt-2 text-sm font-medium text-white">Clique para enviar um comprovante</p>
          <p className="text-xs text-slate-500">PDF, JPG ou PNG até 10 MB</p>
        </div>

        <div className="space-y-2">
          {receipts.map((r) => (
            <div key={r.id} className="flex items-center justify-between rounded-xl border border-slate-800 bg-slate-900 p-4">
              <div className="flex items-center gap-3">
                <div className="flex h-10 w-10 items-center justify-center rounded-lg bg-slate-800 text-slate-300">
                  <FileText className="h-5 w-5" />
                </div>
                <div>
                  <div className="text-sm font-medium text-white">{r.name}</div>
                  <div className="text-xs text-slate-500">
                    {r.size} • {r.date}
                  </div>
                </div>
              </div>
              <div className="flex items-center gap-3">
                <span className={`inline-flex items-center rounded-full px-2 py-0.5 text-xs font-medium ${r.status === "Confirmado" ? "bg-emerald-500/10 text-emerald-400" : "bg-amber-500/10 text-amber-400"}`}>
                  {r.status === "Confirmado" ? <CheckCircle2 className="mr-1 h-3 w-3" /> : <Clock className="mr-1 h-3 w-3" />}
                  {r.status}
                </span>
                <button className="rounded-md p-1 text-slate-500 hover:bg-slate-800 hover:text-white">
                  <MoreVertical className="h-4 w-4" />
                </button>
              </div>
            </div>
          ))}
          {receipts.length === 0 && <div className="py-8 text-center text-sm text-slate-500">Nenhum comprovante enviado ainda.</div>}
        </div>
      </div>
    );
  };

  // Modais
  const ModalRoot = () => {
    if (!modal) return null;

    if (modal.type === "pay" || modal.type === "payForStudent") {
      const payment = modal.type === "pay" ? modal.data.payment : null;
      const [step, setStep] = useState("method");
      const [method, setMethod] = useState("PIX");
      const [loading, setLoading] = useState(false);

      function confirm() {
        setLoading(true);
        setTimeout(() => {
          if (payment) {
            setPayments((prev) => prev.map((p) => (p.id === payment.id ? { ...p, status: "Pago", method, paidAt: today.toISOString().slice(0, 10) } : p)));
          }
          setLoading(false);
          closeModal();
        }, 1500);
      }

      return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/70 p-4 backdrop-blur-sm">
          <div className="w-full max-w-md rounded-2xl border border-slate-800 bg-slate-900 p-6 shadow-2xl">
            <div className="flex items-center justify-between">
              <h3 className="text-lg font-semibold text-white">{payment ? `Pagar ${payment.studentName}` : `Registrar pagamento - ${modal.data.studentName}`}</h3>
              <button onClick={closeModal} className="rounded-md p-1 text-slate-400 hover:bg-slate-800 hover:text-white">
                <X className="h-5 w-5" />
              </button>
            </div>

            {step === "method" && (
              <div className="mt-4 space-y-3">
                <div className="grid grid-cols-2 gap-3">
                  <button onClick={() => setMethod("PIX")} className={`flex flex-col items-center gap-2 rounded-xl border p-4 ${method === "PIX" ? "border-emerald-500 bg-emerald-500/10 text-emerald-400" : "border-slate-800 bg-slate-950 text-slate-400 hover:border-slate-700"}`}>
                    <QrCode className="h-6 w-6" />
                    <span className="text-sm font-medium">PIX</span>
                  </button>
                  <button onClick={() => setMethod("Cartão")} className={`flex flex-col items-center gap-2 rounded-xl border p-4 ${method === "Cartão" ? "border-emerald-500 bg-emerald-500/10 text-emerald-400" : "border-slate-800 bg-slate-950 text-slate-400 hover:border-slate-700"}`}>
                    <CreditCard className="h-6 w-6" />
                    <span className="text-sm font-medium">Cartão</span>
                  </button>
                </div>
                <div className="rounded-lg border border-slate-800 bg-slate-950 p-4">
                  <div className="text-xs text-slate-500">Valor</div>
                  <div className="text-xl font-bold text-white">{payment ? formatCurrency(payment.value) : formatCurrency(150)}</div>
                </div>
                <button onClick={() => setStep("confirm")} className="w-full rounded-lg bg-emerald-600 py-2.5 text-sm font-semibold text-white hover:bg-emerald-500">
                  Continuar
                </button>
              </div>
            )}

            {step === "confirm" && (
              <div className="mt-4 space-y-4">
                {method === "PIX" ? (
                  <div className="flex flex-col items-center gap-3 rounded-xl border border-slate-800 bg-slate-950 p-5">
                    <QrCode className="h-32 w-32 text-white" />
                    <p className="text-xs text-slate-400">Escaneie o QR Code para pagar via PIX</p>
                  </div>
                ) : (
                  <div className="space-y-3">
                    <input placeholder="Nome no cartão" className="w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white placeholder-slate-600 outline-none focus:border-emerald-500" />
                    <input placeholder="Número do cartão" className="w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white placeholder-slate-600 outline-none focus:border-emerald-500" />
                    <div className="grid grid-cols-2 gap-3">
                      <input placeholder="MM/AA" className="w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white placeholder-slate-600 outline-none focus:border-emerald-500" />
                      <input placeholder="CVV" className="w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white placeholder-slate-600 outline-none focus:border-emerald-500" />
                    </div>
                  </div>
                )}
                <div className="flex gap-2">
                  <button onClick={() => setStep("method")} className="rounded-lg border border-slate-800 bg-slate-950 px-4 py-2.5 text-sm font-medium text-white hover:bg-slate-800">
                    <ArrowLeft className="mr-1 inline h-4 w-4" /> Voltar
                  </button>
                  <button onClick={confirm} disabled={loading} className="flex-1 rounded-lg bg-emerald-600 py-2.5 text-sm font-semibold text-white hover:bg-emerald-500 disabled:opacity-50">
                    {loading ? "Processando..." : "Confirmar pagamento"}
                  </button>
                </div>
              </div>
            )}
          </div>
        </div>
      );
    }

    if (modal.type === "newWorkout" || modal.type === "assignWorkout") {
      const [title, setTitle] = useState("");
      const [day, setDay] = useState("Segunda");
      const [studentId, setStudentId] = useState(modal.data?.studentId || students[0]?.id || "");
      const [exercises, setExercises] = useState([{ name: "", sets: 3, reps: "10" }]);

      function addExercise() {
        setExercises((prev) => [...prev, { name: "", sets: 3, reps: "10" }]);
      }

      function removeExercise(idx) {
        setExercises((prev) => prev.filter((_, i) => i !== idx));
      }

      function updateExercise(idx, field, value) {
        setExercises((prev) => prev.map((e, i) => (i === idx ? { ...e, [field]: value } : e)));
      }

      function save() {
        if (!title.trim() || exercises.some((e) => !e.name.trim())) return;
        const newWorkout = {
          id: Date.now(),
          title,
          studentId: Number(studentId),
          day,
          exercises: exercises.map((e) => ({ ...e, sets: Number(e.sets) })),
        };
        setWorkouts((prev) => [...prev, newWorkout]);
        closeModal();
      }

      return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/70 p-4 backdrop-blur-sm">
          <div className="w-full max-w-lg rounded-2xl border border-slate-800 bg-slate-900 p-6 shadow-2xl">
            <div className="flex items-center justify-between">
              <h3 className="text-lg font-semibold text-white">{modal.type === "assignWorkout" ? `Atribuir treino - ${modal.data.studentName}` : "Novo treino"}</h3>
              <button onClick={closeModal} className="rounded-md p-1 text-slate-400 hover:bg-slate-800 hover:text-white">
                <X className="h-5 w-5" />
              </button>
            </div>
            <div className="mt-4 space-y-3">
              <div className="grid grid-cols-2 gap-3">
                <div>
                  <label className="text-xs text-slate-500">Título do treino</label>
                  <input value={title} onChange={(e) => setTitle(e.target.value)} className="mt-1 w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white outline-none focus:border-emerald-500" placeholder="Ex: Treino A" />
                </div>
                <div>
                  <label className="text-xs text-slate-500">Dia da semana</label>
                  <select value={day} onChange={(e) => setDay(e.target.value)} className="mt-1 w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white outline-none focus:border-emerald-500">
                    {["Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"].map((d) => (
                      <option key={d} value={d}>{d}</option>
                    ))}
                  </select>
                </div>
              </div>
              <div>
                <label className="text-xs text-slate-500">Aluno</label>
                <select value={studentId} onChange={(e) => setStudentId(e.target.value)} className="mt-1 w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white outline-none focus:border-emerald-500">
                  {students.map((s) => (
                    <option key={s.id} value={s.id}>{s.name}</option>
                  ))}
                </select>
              </div>

              <div className="max-h-64 space-y-2 overflow-y-auto pr-1">
                {exercises.map((ex, idx) => (
                  <div key={idx} className="flex items-end gap-2">
                    <div className="flex-1">
                      <label className="text-xs text-slate-500">Exercício</label>
                      <input value={ex.name} onChange={(e) => updateExercise(idx, "name", e.target.value)} className="mt-1 w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white outline-none focus:border-emerald-500" placeholder="Nome do exercício" />
                    </div>
                    <div className="w-20">
                      <label className="text-xs text-slate-500">Séries</label>
                      <input type="number" value={ex.sets} onChange={(e) => updateExercise(idx, "sets", e.target.value)} className="mt-1 w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white outline-none focus:border-emerald-500" />
                    </div>
                    <div className="w-24">
                      <label className="text-xs text-slate-500">Reps</label>
                      <input value={ex.reps} onChange={(e) => updateExercise(idx, "reps", e.target.value)} className="mt-1 w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white outline-none focus:border-emerald-500" placeholder="10-12" />
                    </div>
                    <button onClick={() => removeExercise(idx)} className="mb-0.5 rounded-md p-2 text-slate-500 hover:bg-red-500/10 hover:text-red-400">
                      <Trash2 className="h-4 w-4" />
                    </button>
                  </div>
                ))}
              </div>

              <button onClick={addExercise} className="inline-flex items-center gap-1 text-xs font-medium text-emerald-400 hover:text-emerald-300">
                <Plus className="h-3.5 w-3.5" /> Adicionar exercício
              </button>

              <div className="flex gap-2 pt-2">
                <button onClick={closeModal} className="rounded-lg border border-slate-800 bg-slate-950 px-4 py-2.5 text-sm font-medium text-white hover:bg-slate-800">Cancelar</button>
                <button onClick={save} className="flex-1 rounded-lg bg-emerald-600 py-2.5 text-sm font-semibold text-white hover:bg-emerald-500">Salvar treino</button>
              </div>
            </div>
          </div>
        </div>
      );
    }

    if (modal.type === "newStudent") {
      const [name, setName] = useState("");
      const [plan, setPlan] = useState("Básico");
      function saveStudent() {
        if (!name.trim()) return;
        const newStudent = { id: Date.now(), name, plan, status: "Ativo", since: today.toISOString().slice(0, 10), phone: "" };
        setStudents((prev) => [...prev, newStudent]);
        closeModal();
      }
      return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/70 p-4 backdrop-blur-sm">
          <div className="w-full max-w-md rounded-2xl border border-slate-800 bg-slate-900 p-6 shadow-2xl">
            <div className="flex items-center justify-between">
              <h3 className="text-lg font-semibold text-white">Novo aluno</h3>
              <button onClick={closeModal} className="rounded-md p-1 text-slate-400 hover:bg-slate-800 hover:text-white">
                <X className="h-5 w-5" />
              </button>
            </div>
            <div className="mt-4 space-y-3">
              <div>
                <label className="text-xs text-slate-500">Nome completo</label>
                <input value={name} onChange={(e) => setName(e.target.value)} className="mt-1 w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white outline-none focus:border-emerald-500" placeholder="Nome do aluno" />
              </div>
              <div>
                <label className="text-xs text-slate-500">Plano</label>
                <select value={plan} onChange={(e) => setPlan(e.target.value)} className="mt-1 w-full rounded-lg border border-slate-800 bg-slate-950 px-3 py-2 text-sm text-white outline-none focus:border-emerald-500">
                  {["Básico", "Intermediário", "Premium"].map((p) => (
                    <option key={p} value={p}>{p}</option>
                  ))}
                </select>
              </div>
              <div className="flex gap-2 pt-2">
                <button onClick={closeModal} className="rounded-lg border border-slate-800 bg-slate-950 px-4 py-2.5 text-sm font-medium text-white hover:bg-slate-800">Cancelar</button>
                <button onClick={saveStudent} className="flex-1 rounded-lg bg-emerald-600 py-2.5 text-sm font-semibold text-white hover:bg-emerald-500">Cadastrar</button>
              </div>
            </div>
          </div>
        </div>
      );
    }

    return null;
  };

  return (
    <div className="flex min-h-screen bg-slate-950 text-slate-200">
      {/* Sidebar */}
      <aside className={`${sidebarOpen ? "w-64" : "w-16"} shrink-0 border-r border-slate-800 bg-slate-900 transition-all duration-300`}>
        <div className="flex h-16 items-center gap-3 px-4">
          <div className="flex h-8 w-8 shrink-0 items-center justify-center rounded-lg bg-emerald-600 text-white font-bold">G</div>
          {sidebarOpen && <span className="font-bold text-white tracking-tight">GymPro</span>}
        </div>
        <nav className="mt-4 space-y-1 px-2">
          {navItems.map((item) => {
            const Icon = item.icon;
            const active = activeTab === item.id;
            return (
              <button
                key={item.id}
                onClick={() => setActiveTab(item.id)}
                className={`flex w-full items-center gap-3 rounded-lg px-3 py-2 text-sm font-medium transition-colors ${active ? "bg-emerald-600 text-white" : "text-slate-400 hover:bg-slate-800 hover:text-white"}`}
                title={item.label}
              >
                <Icon className="h-5 w-5 shrink-0" />
                {sidebarOpen && <span>{item.label}</span>}
              </button>
            );
          })}
        </nav>
        <div className="mt-auto p-4">
          <button onClick={() => setSidebarOpen(!sidebarOpen)} className="flex w-full items-center justify-center rounded-lg border border-slate-800 bg-slate-950 py-2 text-xs font-medium text-slate-400 hover:text-white">
            {sidebarOpen ? "Recolher" : "Expandir"}
          </button>
        </div>
      </aside>

      {/* Main */}
      <main className="flex-1">
        <header className="flex h-16 items-center justify-between border-b border-slate-800 bg-slate-900/50 px-6 backdrop-blur">
          <h1 className="text-sm font-semibold text-white capitalize">{navItems.find((n) => n.id === activeTab)?.label}</h1>
          <div className="flex items-center gap-3">
             <div className="h-8 w-8 rounded-full bg-emerald-600 text-xs flex items-center justify-center font-bold text-white">AD</div>
          </header>

          <div className="p-6">
            {activeTab === "dashboard" && <DashboardView />}
            {activeTab === "alunos" && <AlunosView />}
            {activeTab === "treinos" && <TreinosView />}
            {activeTab === "pagamentos" && <PagamentosView />}
            {activeTab === "comprovantes" && <ComprovantesView />}
          </div>

          <ModalRoot />
        </main>
      </div>
    );
  }