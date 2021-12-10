public class KDTree3D {

	LinkedBinaryTree<Point3D> tree;

	public KDTree3D() {
		tree = new LinkedBinaryTree <Point3D>();
	}

	public void insert(Point3D point) {
		if (tree.size() == 0) {
			tree.addRoot(point);
			return;
		}
		Position<Point3D> currentNode = tree.root();
		int d = 0;
		while(true) {
			if (d  == 0) { // x
				if (currentNode.getElement().getX() > point.getX()) {
					if (tree.left(currentNode) == null) {
						tree.addLeft(currentNode,point);
						break;
					}
					else {
						currentNode = tree.left(currentNode);
					}
				}
				else {
					if (tree.right(currentNode) == null) {
						tree.addRight(currentNode,point);
						break;
					}
					else {
						currentNode = tree.right(currentNode);
					}
				}
			}

			if (d == 1) { // y
				if (currentNode.getElement().getY() > point.getY()) {
					if (tree.left(currentNode) == null) {
						tree.addLeft(currentNode,point);
						break;
					}
					else {
						currentNode = tree.left(currentNode);
					}
				}
				else {
					if (tree.right(currentNode) == null) {
						tree.addRight(currentNode,point);
						break;
					}
					else {
						currentNode = tree.right(currentNode);
					}
				}
			}

			if (d == 2) { // z
				if (currentNode.getElement().getZ() > point.getZ()) {
					if (tree.left(currentNode) == null) {
						tree.addLeft(currentNode,point);
						break;
					}
					else {
						currentNode = tree.left(currentNode);
					}
				}
				else {
					if (tree.right(currentNode) == null) {
						tree.addRight(currentNode,point);
						break;
					}
					else {
						currentNode = tree.right(currentNode);
					}
				}

			}
			d = (d + 1) % 3;
		}
 	}

 	private Position<Point3D> mySearch(Point3D point) {
 		if (tree.size() == 0)
 			return null;
		Position<Point3D> currentNode = tree.root();
		int d = 0;
		while(currentNode != null) {
			if (currentNode.getElement().equals(point))
				return currentNode;
			if (d  == 0) { // x
				if (currentNode.getElement().getX() > point.getX()) {
					currentNode = tree.left(currentNode);
				}
				else {
					currentNode = tree.right(currentNode);
				}
			}

			if (d == 1) { // y
				if (currentNode.getElement().getY() > point.getY()) {
					currentNode = tree.left(currentNode);
				}
				else {
					currentNode = tree.right(currentNode);
				}
			}

			if (d == 2) { // z
				if (currentNode.getElement().getZ() > point.getZ()) {
					currentNode = tree.left(currentNode);
				}
				else {
					currentNode = tree.right(currentNode);
				}
			}
			d = (d + 1) % 3;
		}
		return null;
 	}

 	public Point3D search(Point3D point) {
 		if (tree.size() == 0)
 			return null;
 		Position<Point3D> pos = mySearch(point);
 		if (pos == null)
 			return null;
 		return pos.getElement();
 	} 	

 	private int findD(Position<Point3D> pos) {
 		int height = 0;
 		while(tree.root().getElement().equals(pos.getElement()) == false) {
 			height++;
 			pos = tree.parent(pos);
 		}
 		return height % 3;
 	}

 	public void remove(Position<Point3D> pos) {
 		int d = findD(pos);
 		if (tree.right(pos) == null && tree.left(pos) == null) {
 			tree.remove(pos);
 			return;
 		}
 		if (tree.right(pos) != null) {
 			Position<Point3D> newPos = findMin(tree.right(pos),d + 1,d);
 			pos.getElement().set(newPos.getElement());
 			remove(newPos);
 			return;
 		}
		Position<Point3D> newPos = findMax(tree.left(pos),d + 1,d);
		pos.getElement().set(newPos.getElement());
		remove(newPos);
 	}

 	public void remove(Point3D point) {
 		Position <Point3D> pos = mySearch(point);
 		if (pos != null)
 			remove(pos);
 	}

 	private Position<Point3D> findMin(Position<Point3D> currentNode,int depth,int d) {
 		if (depth % 3 == d) {
 			if (tree.left(currentNode) != null)
 				return findMin(tree.left(currentNode),depth + 1,d);
 			return currentNode;
 		}
 		Position<Point3D> result = currentNode;
 		if (tree.right(currentNode) != null) {
 			Position<Point3D> p = findMin(tree.right(currentNode),depth + 1,d);
 			if (p.getElement().getD(d) < result.getElement().getD(d))
 				result = p;
 		}
 		if (tree.left(currentNode) != null) {
 			Position<Point3D> p = findMin(tree.left(currentNode),depth + 1,d);
 			if (p.getElement().getD(d) < result.getElement().getD(d))
 				result = p;
 		}
 		return result;
 	}

 	public Point3D findMin(int d) {
 		return findMin(tree.root(),0,d).getElement();
 	}

 	private Position<Point3D> findMax(Position<Point3D> currentNode,int depth,int d) {
 		if (depth % 3 == d) {
 			if (tree.right(currentNode) != null)
 				return findMax(tree.right(currentNode),depth + 1,d);
 			return currentNode;
 		}
 		Position<Point3D> result = currentNode;
 		if (tree.right(currentNode) != null) {
 			Position<Point3D> p = findMax(tree.right(currentNode),depth + 1,d);
 			if (p.getElement().getD(d) > result.getElement().getD(d))
 				result = p;
 		}
 		if (tree.left(currentNode) != null) {
 			Position<Point3D> p = findMax(tree.left(currentNode),depth + 1,d);
 			if (p.getElement().getD(d) > result.getElement().getD(d))
 				result = p;
 		}
 		return result;
 		
 	}

 	public Point3D findMax(int d) {
 		return findMax(tree.root(),0,d).getElement();
 	} 	

 	private void displayTree(Position<Point3D> currentNode, int depth) {
 		if (currentNode == null)
 			return;
 		for (int i = 0 ; i < depth ; i++)
 			System.out.print(". ");
 		System.out.println(currentNode.getElement());
		displayTree(tree.left(currentNode),depth + 1);
		displayTree(tree.right(currentNode),depth + 1);
 	}

 	public void displayTree() {
 		displayTree(tree.root(),0);
 	}

 	private void printRange(Position<Point3D> currentNode,int depth,Point3D fll, Point3D bur) {
 		if (currentNode == null)
 			return;
 		double x = currentNode.getElement().getX();
 		double y = currentNode.getElement().getY();
 		double z = currentNode.getElement().getZ();
 		if (fll.getX() <= x && x <= bur.getX() && fll.getY() <= y && y <= bur.getY() && fll.getZ() <= z && z <= bur.getZ())
 			System.out.println(currentNode.getElement());
 		if (depth % 3 == 0) { //X
 			if (fll.getX() <= x)
 				printRange(tree.left(currentNode),depth + 1,fll,bur);
 			if (x <= bur.getX())
 				printRange(tree.right(currentNode),depth + 1,fll,bur);
 		}
 		else if (depth % 3 == 0) { //Y
 			if (fll.getY() <= y)
 				printRange(tree.left(currentNode),depth + 1,fll,bur);
 			if (y <= bur.getY())
 				printRange(tree.right(currentNode),depth + 1,fll,bur); 			
 		}
 		else { //Z
 			if (fll.getZ() <= z)
 				printRange(tree.left(currentNode),depth + 1,fll,bur);
 			if (z <= bur.getZ())
 				printRange(tree.right(currentNode),depth + 1,fll,bur);
 		}

 	}

 	public void printRange(Point3D fll, Point3D bur) {
 		printRange(tree.root(),0,fll,bur);
 	}

}