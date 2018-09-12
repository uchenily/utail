如果你还不够了解 TensorFlow.js，可以右转：here。下面是关于一些关于 TensorFlow.js 核心概念。

由于现在 TensorFlow.js 的资料仅限于官方，也没什么中文资料，这篇由LiNPX整理、收集与翻译


# Tensors (张量)
张量（tensor）是一个可用来表示在一些矢量、纯量和其他张量之间的线性关系的多线性函数，这些线性关系的基本例子有内积、外积、线性映射以及笛卡儿积。其坐标在 n  维空间内，有  n^r个分量的一种量，其中每个分量都是坐标的函数，而在坐标变换时，这些分量也依照某些规则作线性变换。r称为该张量的秩或阶（与矩阵的秩和阶均无关系）。

TensorFlow.js 的数据单元是张量：一组数值存储于一维或者多维数组里。一个张量的实例有 shape 的属性用于构造多维数组。其中最主要的 Tensor 的构造函数是 tf.tensor 。

	// 2x3 Tensor
	const shape = [2, 3]; // 2 rows, 3 columns
	const a = tf.tensor([1.0, 2.0, 3.0, 10.0, 20.0, 30.0], shape);
	a.print(); // print Tensor values
	// Output: [[1 , 2 , 3 ],
	//          [10, 20, 30]]
	
	// The shape can also be inferred:
	const b = tf.tensor([[1.0, 2.0, 3.0], [10.0, 20.0, 30.0]]);
	b.print();
	// Output: [[1 , 2 , 3 ],
	//          [10, 20, 30]]
但有时候，为了方便构造更简单的 Tensors，建议使用 tf.scalar, tf.tensor1d, tf.tensor2d, tf.tensor3d 和 tf.tensor4d，这样也会更强代码的可读性。下面的例子使用了tf.tensor2d：

	const c = tf.tensor2d([[1.0, 2.0, 3.0], [10.0, 20.0, 30.0]]);
	c.print();
	// Output: [[1 , 2 , 3 ],
	//          [10, 20, 30]]
TensorFlow.js 也提供了用于构建值全部为 0 的 tensors 的tf.zeros 函数和构建值为 1 的tf.ones的函数

	// 3x5 Tensor with all values set to 0
	const zeros = tf.zeros([3, 5]);
	// Output: [[0, 0, 0, 0, 0],
	//          [0, 0, 0, 0, 0],
	//          [0, 0, 0, 0, 0]]
在 TensorFlow.js 中，tensors 一旦被构造就具备不可变性，你不能再修改它们的值，只能重新创建。


# Variables(变量)
Variables 是由 tensor 的值来初始化的。跟 tensors 不同，Variables是可变的。你可以通过assign 使用已经存在的Variables 去声明一个新的 tensor。

	const initialValues = tf.zeros([5]);
	const biases = tf.variable(initialValues); // initialize biases
	biases.print(); // output: [0, 0, 0, 0, 0]
	
	const updatedValues = tf.tensor1d([0, 1, 0, 1, 0]);
	biases.assign(updatedValues); // update values of biases
	biases.print(); // output: [0, 1, 0, 1, 0]
Variables 主要用于在训练的时候，存储或者更新数值。


# Operations(Ops)
使用 tensors 去存储数据，那么 Operations 允许你去利用这些数据。TensorFlow.js 提供了一整套适用于线性代数和机器学习的操作函数。因为 tensors 不可变，所以这些 Ops 不会修改到它们的值，只会返回一个全新的 tensors。

下面是对张量中的值进行平方计算：

	const d = tf.tensor2d([[1.0, 2.0], [3.0, 4.0]]);
	const d_squared = d.square();
	d_squared.print();
	// Output: [[1, 4 ],
	//          [9, 16]]
也可以两个张量相加：

	const e = tf.tensor2d([[1.0, 2.0], [3.0, 4.0]]);
	const f = tf.tensor2d([[5.0, 6.0], [7.0, 8.0]]);
	
	const e_plus_f = e.add(f);
	e_plus_f.print();
	// Output: [[6 , 8 ],
	//          [10, 12]]
当然，TensorFlow.js Ops支持链式写法：

	const sq_sum = e.add(f).square();
	sq_sum.print();
	// Output: [[36 , 64 ],
	//          [100, 144]]
	
	// All operations are also exposed as functions in the main namespace,
	// so you could also do the following:
	const sq_sum = tf.square(tf.add(e, f));


# Models 和 Layers
从概念上讲，Models 可以被认为是函数，输入一些参数就会输出预测结果值。

在 TensorFlow.js 中，有两种创建模型的方法。你可以使用 Ops 直接创建一个可用的模型，如下：

	// Define function
	function predict(input) {
	  // y = a * x ^ 2 + b * x + c
	  // More on tf.tidy in the next section
	  return tf.tidy(() => {
	    const x = tf.scalar(input);

	    const ax2 = a.mul(x.square());
	    const bx = b.mul(x);
	    const y = ax2.add(bx).add(c);
	
	    return y;
	});
	}

	// Define constants: y = 2x^2 + 4x + 8
	const a = tf.scalar(2);
	const b = tf.scalar(4);
	const c = tf.scalar(8);
	
	// Predict output for input of 2
	const result = predict(2);
	result.print() // Output: 24
你也可以使用更高层的 API tf.model 从层次上去构造模型，这是现在深度学习流行的抽象。使用 tf.sequential 去构建模型：

	const model = tf.sequential();
	model.add(
	  tf.layers.simpleRNN({
	    units: 20,
	    recurrentInitializer: 'GlorotNormal',
	    inputShape: [80, 4]
	  })
	);
	
	const optimizer = tf.train.sgd(LEARNING_RATE);
	model.compile({optimizer, loss: 'categoricalCrossentropy'});
	model.fit({x: data, y: labels)});
TensorFlow.js中有许多不同类型的图层，包括tf.layers.simpleRNN, tf.layers.gru 和 tf.layers.lstm。


# Memory Management
因为 TensorFlow.js 使用 GPU 来加速数学运算，所以在运行时就需要管理 GPU 的内存。

TensorFlow.js 提供两种函数帮助处理 GPU 内存：dispose 和 tf.tidy。

## dispose
你可以调用dispose来清空已占用的 GPU 内存：

	const x = tf.tensor2d([[0.0, 2.0], [4.0, 6.0]]);
	const x_squared = x.square();
	
	x.dispose();
	x_squared.dispose();
## tf.tidy
如果要进行大量 tensors 操作时，调用dispose将会变得很麻烦。那么这个时候就需要 tf.tidy ,它对有点像 JavaScript 的作用域，但只针对 GPU-backed tensors。

tf.tidy 执行后就会清除所有中间新建的 tensors 来达到释放 GPU 内存，但不会消除内部的返回值。

	// tf.tidy takes a function to tidy up after
	const average = tf.tidy(() => {
	  // tf.tidy will clean up all the GPU memory used by tensors inside
	  // this function, other than the tensor that is returned.
	  //
	  // Even in a short sequence of operations like the one below, a number
	  // of intermediate tensors get created. So it is a good practice to
	  // put your math ops in a tidy!
	  const y = tf.tensor1d([1.0, 2.0, 3.0, 4.0]);
	  const z = tf.ones([4]);
	
	  return y.sub(z).square().mean();
	});

	average.print() // Output: 3.5
使用 tf.tidy 有助于预防应用出现内存泄露。当然它更能用于精准控制内存何时被回收。

## 两条重要提示
- tf.tidy使用是同步的，而且不会返回 Promise 对象。建议保持UI代码更新，或者在 tf.tidy调用后才发起远程请求；

- tf.tidy不会清理变量。变量会一直保留到整个机器学习模型的生命周期里，因此 TensorFlow.js 不会清理它们即便是在干净的环境下创建的。但你可以使用 dispose 手动清理。


# 其他资料
- [Machine Learning Crash Course](http://example.com)

- Machine Learning Glossary

- This is [an example](http://example.com/ "Title") inline link.

- [This link](http://example.net/) has no title attribute.
