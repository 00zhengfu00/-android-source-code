.class public Ljavolution/context/HeapContext;
.super Ljavolution/context/AllocatorContext;


# static fields
.field private static final ACTIVE_ALLOCATORS:Ljava/lang/ThreadLocal;

.field private static final CLASS:Ljava/lang/Class;

.field private static final FACTORY_TO_ALLOCATOR:Ljava/lang/ThreadLocal;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Ljavolution/context/HeapContext$1;

    invoke-direct {v0}, Ljavolution/context/HeapContext$1;-><init>()V

    sput-object v0, Ljavolution/context/HeapContext;->FACTORY_TO_ALLOCATOR:Ljava/lang/ThreadLocal;

    new-instance v0, Ljavolution/context/HeapContext$2;

    invoke-direct {v0}, Ljavolution/context/HeapContext$2;-><init>()V

    sput-object v0, Ljavolution/context/HeapContext;->ACTIVE_ALLOCATORS:Ljava/lang/ThreadLocal;

    new-instance v0, Ljavolution/context/HeapContext;

    invoke-direct {v0}, Ljavolution/context/HeapContext;-><init>()V

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    sput-object v0, Ljavolution/context/HeapContext;->CLASS:Ljava/lang/Class;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljavolution/context/AllocatorContext;-><init>()V

    return-void
.end method

.method public static enter()Ljavolution/context/HeapContext;
    .locals 1

    sget-object v0, Ljavolution/context/HeapContext;->CLASS:Ljava/lang/Class;

    invoke-static {v0}, Ljavolution/context/Context;->enter(Ljava/lang/Class;)Ljavolution/context/Context;

    move-result-object v0

    check-cast v0, Ljavolution/context/HeapContext;

    return-object v0
.end method

.method public static exit()Ljavolution/context/HeapContext;
    .locals 1

    invoke-static {}, Ljavolution/context/Context;->exit()Ljavolution/context/Context;

    move-result-object v0

    check-cast v0, Ljavolution/context/HeapContext;

    return-object v0
.end method


# virtual methods
.method protected deactivate()V
    .locals 5

    sget-object v0, Ljavolution/context/HeapContext;->ACTIVE_ALLOCATORS:Ljava/lang/ThreadLocal;

    invoke-virtual {v0}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljavolution/util/FastTable;

    const/4 v1, 0x0

    invoke-virtual {v0}, Ljavolution/util/FastTable;->size()I

    move-result v3

    :goto_0
    if-ge v1, v3, :cond_0

    add-int/lit8 v2, v1, 0x1

    invoke-virtual {v0, v1}, Ljavolution/util/FastTable;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljavolution/context/Allocator;

    const/4 v4, 0x0

    iput-object v4, v1, Ljavolution/context/Allocator;->user:Ljava/lang/Thread;

    move v1, v2

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Ljavolution/util/FastTable;->clear()V

    return-void
.end method

.method protected enterAction()V
    .locals 1

    invoke-virtual {p0}, Ljavolution/context/HeapContext;->getOuter()Ljavolution/context/Context;

    move-result-object v0

    invoke-virtual {v0}, Ljavolution/context/Context;->getAllocatorContext()Ljavolution/context/AllocatorContext;

    move-result-object v0

    invoke-virtual {v0}, Ljavolution/context/AllocatorContext;->deactivate()V

    return-void
.end method

.method protected exitAction()V
    .locals 0

    invoke-virtual {p0}, Ljavolution/context/HeapContext;->deactivate()V

    return-void
.end method

.method protected getAllocator(Ljavolution/context/ObjectFactory;)Ljavolution/context/Allocator;
    .locals 2

    sget-object v0, Ljavolution/context/HeapContext;->FACTORY_TO_ALLOCATOR:Ljava/lang/ThreadLocal;

    invoke-virtual {v0}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljavolution/util/FastMap;

    invoke-virtual {v0, p1}, Ljavolution/util/FastMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljavolution/context/HeapContext$HeapAllocator;

    if-nez v1, :cond_0

    new-instance v1, Ljavolution/context/HeapContext$HeapAllocator;

    invoke-direct {v1, p1}, Ljavolution/context/HeapContext$HeapAllocator;-><init>(Ljavolution/context/ObjectFactory;)V

    invoke-virtual {v0, p1, v1}, Ljavolution/util/FastMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_0
    iget-object v0, v1, Ljavolution/context/HeapContext$HeapAllocator;->user:Ljava/lang/Thread;

    if-nez v0, :cond_1

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v0

    iput-object v0, v1, Ljavolution/context/HeapContext$HeapAllocator;->user:Ljava/lang/Thread;

    sget-object v0, Ljavolution/context/HeapContext;->ACTIVE_ALLOCATORS:Ljava/lang/ThreadLocal;

    invoke-virtual {v0}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljavolution/util/FastTable;

    invoke-virtual {v0, v1}, Ljavolution/util/FastTable;->add(Ljava/lang/Object;)Z

    :cond_1
    return-object v1
.end method
